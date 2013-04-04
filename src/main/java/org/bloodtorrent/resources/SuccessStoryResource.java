package org.bloodtorrent.resources;

import com.google.common.io.Files;
import com.google.common.io.InputSupplier;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.views.View;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.SuccessStoryRepository;
import org.bloodtorrent.view.LoginView;
import org.bloodtorrent.view.ResultView;
import org.bloodtorrent.view.SuccessStoryView;
import org.eclipse.jetty.server.SessionManager;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

@Path("/successStory")
@Produces(MediaType.TEXT_HTML)
public class SuccessStoryResource {
    public static final int STATUS_MOVED = 302;
    public static final int STATUS_NOT_FOUND = 404;
    public static final String UPLOAD_DIR = "upload";
    public static final String JSESSIONID = "JSESSIONID";
    public static final String USER = "user";
    public static final int MIN_SUCCESS_STORIES_TO_SHOW = 1;
    public static final int MAX_SUCCESS_STORIES_TO_SHOW = 3;
    private final SuccessStoryRepository repository;
    private SessionManager sessionManager;

    protected SuccessStoryResource(SessionManager httpSessionManager, SuccessStoryRepository repository) {

        sessionManager = httpSessionManager;

        this.repository = repository;
    }

    /**
     * Get at most 3 Success Stories. This will be used for being shown on main page.
     * @return
     */
    public List<SuccessStory> getSuccessStoriesBriefly(){
        List<SuccessStory> list = repository.list();

        List<SuccessStory> result = new LinkedList<SuccessStory>();
        int index = 0;
        for(SuccessStory story : list){
            result.add(story);
            index++;
            if(index == SuccessStory.MAXIMUM_SHOWN_STORY_ON_MAIN_PAGE) break;
        }

        return result;
    }

	public SuccessStory get(String id) {
		return repository.get(id);
	}

	@GET
	@UnitOfWork
    @Path("{id}")
	public SuccessStoryView getSuccessStory(@PathParam("id") String id, @CookieParam(JSESSIONID) String sessionID) {
        HttpSession session = null;
        User user = null;

        if (sessionID != null) {
            session = sessionManager.getHttpSession(sessionID);
        }
        if (session != null) {
            user = (User)session.getAttribute(USER);
        }

		SuccessStory successStory = repository.get(id);
        SuccessStoryView successStoryView = new SuccessStoryView(successStory);
        successStoryView.setUser(user);
        return successStoryView;
	}

    @GET
    @UnitOfWork
    @Path("image/{fileName}")
    public Response loadImage(@PathParam("fileName") String fileName) {
        File imageFile = new File("upload/" + fileName);

        Response.ResponseBuilder builder = null;
        if (imageFile.exists()) {
            String extention = fileName.substring(fileName.lastIndexOf('.') + 1);
            if (extention.equalsIgnoreCase("jpg")) {
                extention = "jpeg";
            }
            String mediaType = "image/" + extention;
            builder = Response.ok((Object) imageFile, mediaType);
        } else {
            builder = Response.status(STATUS_NOT_FOUND);
        }
        return builder.build();
    }

    @GET
	@UnitOfWork
    @Path("list")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
	public Response listSuccessStory(@CookieParam(JSESSIONID) String sessionID) {
        if(sessionID != null){
            HttpSession session = sessionManager.getHttpSession(sessionID);
            User user = null;
            if (session != null) {
                user = (User)session.getAttribute(USER);
            }

            if (user != null && 'Y' == user.getIsAdmin()) {
                SuccessStoryView view = new SuccessStoryView(repository.getListForSuccessStoriesView());
                view.setUser(user);
                return Response.ok(view).build();
            }
        }
        return Response.ok(new LoginView()).build();
	}

    @GET
	@UnitOfWork
    @Path("createView")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
	public Response viewSuccessStoryEditor(@CookieParam(JSESSIONID) String sessionID) {
        HttpSession session = sessionManager.getHttpSession(sessionID);
        User  user = null;
        if (session != null) {
            user = (User)session.getAttribute(USER);
        }

        if (user != null && 'Y' == user.getIsAdmin()) {
            SuccessStoryView view = new SuccessStoryView();
            view.setUser(user);
            return Response.ok(view).build();
        } else {
            return Response.seeOther(URI.create("/")).status(STATUS_MOVED).build();
        }
	}

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("create")
    @UnitOfWork
    public View createSuccessStory(
            @CookieParam(JSESSIONID) String sessionID,
            @FormDataParam("title") String title,
            @FormDataParam("summary") String summary,
            @FormDataParam("description") String description,
            @FormDataParam("visualResourcePath") final InputStream stream,
            @FormDataParam("visualResourcePath") final FormDataContentDisposition content) throws IOException {

        HttpSession session = sessionManager.getHttpSession(sessionID);
        User  user = null;
        if (session == null || stream == null || content == null || session.getAttribute(USER) == null) {
            throw new IOException("************* Something is rotten!");
        }
        if (session != null) {
            user = (User)session.getAttribute(USER);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date timestamp = new Date();

        SuccessStory story = new SuccessStory();
        String id = sdf.format(timestamp);
        story.setId(id);
        story.setTitle(title);
        story.setSummary(summary);
        story.setDescription(description);
        story.setShowMainPage("N");

       if(stream != null && !content.getFileName().isEmpty()){
            final String fileName = id + "-" + content.getFileName();
            saveFile(UPLOAD_DIR, fileName, stream);
            story.setThumbnailPath(fileName);
            story.setVisualResourcePath(fileName);
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
            Set<ConstraintViolation<SuccessStory>> constraintViolations = validator.validate(story);

        if(constraintViolations.size() > 0){
            List<String > messages = new ArrayList<String>();
            for(ConstraintViolation constraintViolation :constraintViolations){
                messages.add(constraintViolation.getMessage()) ;
            }
            return new ResultView("fail", messages);
        } else {
            repository.insert(story);
            SuccessStoryView successStoryView = new SuccessStoryView(repository.getListForSuccessStoriesView());
            successStoryView.setSavedSuccessFlag(true);
            successStoryView.setUser(user);
            return successStoryView;
        }
    }

    private void saveFile(String outputPath, String fileName, final InputStream stream) throws IOException {
        File uploadDir = new File(outputPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }
        final String filePath = outputPath + File.separator + fileName;
        System.out.println("uploadDir = " + uploadDir.getAbsolutePath());
        System.out.println("file = " + filePath);
        Files.copy(new InputSupplier<InputStream>() {
            public InputStream getInput() throws IOException {
                return stream;
            }
        }, new File(filePath));
    }

    @POST
    @Path("/selectForMain")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @UnitOfWork
    public Response selectForMain(@CookieParam(JSESSIONID) String sessionID,
                                  @FormParam("checkStoryId") List<String> checkStoryId) {
        HttpSession session = sessionManager.getHttpSession(sessionID);
        User user = null;
        if (session != null) {
            user = (User)session.getAttribute(USER);
        }
        if (checkStoryId.size() >= MIN_SUCCESS_STORIES_TO_SHOW && checkStoryId.size() <= MAX_SUCCESS_STORIES_TO_SHOW) {
            repository.selectForMain(checkStoryId);
        }
        SuccessStoryView view = new SuccessStoryView(repository.getListForSuccessStoriesView());
        view.setEditSuccessFlag(true);
        view.setUser(user);
        return Response.ok(view).build();
    }
}
