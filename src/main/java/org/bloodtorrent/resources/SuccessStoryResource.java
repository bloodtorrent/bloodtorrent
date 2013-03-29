package org.bloodtorrent.resources;

import com.google.common.io.Files;
import com.google.common.io.InputSupplier;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.repository.SuccessStoryRepository;
import org.bloodtorrent.view.SuccessStoryView;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Path("/successStory")
@Produces(MediaType.TEXT_HTML)
public class SuccessStoryResource {
    private final SuccessStoryRepository repository;
    private String UPLOAD_DIR = "upload";

    public SuccessStoryResource(SuccessStoryRepository repository) {
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

        System.out.println("File = " + imageFile.getAbsolutePath());
        Response.ResponseBuilder builder = null;
        if (imageFile.exists()) {
            System.out.println("File size = " + imageFile.length());
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
	public SuccessStoryView listSuccessStory() {
		return new SuccessStoryView(repository.getListForSuccessStoriesView());
	}
    @GET
	@UnitOfWork
    @Path("createView")
	public SuccessStoryView viewSuccessStoryEditor() {
		return new SuccessStoryView();
	}

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("create")
    @UnitOfWork
    public SuccessStoryView createSuccessStory(@FormDataParam("title") String title,
                                               @FormDataParam("summary") String summary,
                                               @FormDataParam("description") String description,
                                               @FormDataParam("visualResourcePath") final InputStream resource,
                                               @FormDataParam("visualResourcePath") final FormDataContentDisposition content) throws IOException {
        SuccessStory story = new SuccessStory();
        String id = UUID.randomUUID().toString();
        story.setId(id);
        story.setTitle(title);
        story.setSummary(summary);
        story.setDescription(description);
        story.setShowMainPage("Y");
        if(resource != null){
            final String fileName = id + "-" + content.getFileName();
            saveFile(UPLOAD_DIR, fileName, resource);
            story.setThumbnailPath(fileName);
            story.setVisualResourcePath(fileName);
        }
        repository.insert(story);

        return new SuccessStoryView(repository.getListForSuccessStoriesView());
    }

    private void saveFile(String outputPath, String fileName, final InputStream resource) throws IOException {
        File uploadDir = new File(outputPath);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }
        final String filePath = outputPath + File.separator + fileName;
        System.out.println("uploadDir = " + uploadDir.getAbsolutePath());
        System.out.println("file = " + filePath);
        Files.copy(new InputSupplier<InputStream>() {
            public InputStream getInput() throws IOException {
                return resource;
            }
        }, new File(filePath));
    }
}
