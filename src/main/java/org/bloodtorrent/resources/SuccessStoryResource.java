package org.bloodtorrent.resources;

import com.google.common.io.Files;
import com.google.common.io.InputSupplier;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import com.yammer.dropwizard.views.View;
import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.dto.BloodRequest;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.dto.User;
import org.bloodtorrent.repository.SuccessStoryRepository;
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
    public static String UPLOAD_DIR = "upload";
    private final SuccessStoryRepository repository;
    private SessionManager sessionManager;

    public SuccessStoryResource(SuccessStoryRepository repository) {
        this.repository = repository;
    }

    public SuccessStoryResource(SessionManager httpSessionManager, SuccessStoryRepository repository) {

        sessionManager = httpSessionManager;

        this.repository = repository;
    }

    /**
     * Get at most 3 Success Stories. This will be used for being shown on main page.
     * @return
     * @throws IllegalDataException Thrown unless the number of shown success stories is between 0 and MAXIMUM_SHOWN_STORY_ON_MAIN_PAGE.
     */
    public List<SuccessStory> getSuccessStoriesBriefly() throws IllegalDataException {
        List<SuccessStory> list = repository.list();

//        if (list.size() > SuccessStory.MAXIMUM_SHOWN_STORY_ON_MAIN_PAGE) {
//            throw new IllegalDataException("At most " + SuccessStory.MAXIMUM_SHOWN_STORY_ON_MAIN_PAGE + " Success Stories should be shown.");
//        }

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
	public SuccessStoryView getSuccessStory(@PathParam("id") String id) {
		SuccessStory successStory = repository.get(id);
		return new SuccessStoryView(successStory);
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
            builder.header("Content-Encoding", "decompressed");
        } else {
            builder = Response.status(404);
        }
        return builder.build();
    }

    @GET
	@UnitOfWork
    @Path("list")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
	public Response listSuccessStory(@CookieParam("JSESSIONID") String sessionID) {
        HttpSession session = sessionManager.getHttpSession(sessionID);
        User user = null;
        if (session != null) {
            user = (User)session.getAttribute("user");
        }

        if (user != null && 'Y' == user.getIsAdmin()) {
//    		return new SuccessStoryView(repository.getListForSuccessStoriesView());
            SuccessStoryView view = new SuccessStoryView(repository.getListForSuccessStoriesView());
            view.setUser(user);
            return Response.ok(view).build();
        } else {
            return Response.seeOther(URI.create("/")).build();
        }
	}

    @GET
	@UnitOfWork
    @Path("createView")
    @Produces(MediaType.TEXT_HTML + ";charset=utf-8")
	public Response viewSuccessStoryEditor(@CookieParam("JSESSIONID") String sessionID) {
        HttpSession session = sessionManager.getHttpSession(sessionID);
        User  user = null;
        if (session != null) {
            user = (User)session.getAttribute("user");
        }

        if (user != null && 'Y' == user.getIsAdmin()) {
            //return new SuccessStoryView();
            SuccessStoryView view = new SuccessStoryView();
            view.setUser(user);
            return Response.ok(view).build();
        } else {
            return Response.seeOther(URI.create("/")).status(302).build();
        }
	}

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("create")
    @UnitOfWork
    public View createSuccessStory(@FormDataParam("title") String title,
                                               @FormDataParam("summary") String summary,
                                               @FormDataParam("description") String description,
                                               @FormDataParam("visualResourcePath") final InputStream stream,
                                               @FormDataParam("visualResourcePath") final FormDataContentDisposition content) throws IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date timestamp = new Date();

        SuccessStory story = new SuccessStory();
        String id = sdf.format(timestamp);
        story.setId(id);
        story.setTitle(title);
        story.setSummary(summary);
        story.setDescription(description);
        story.setShowMainPage("Y");

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
}
