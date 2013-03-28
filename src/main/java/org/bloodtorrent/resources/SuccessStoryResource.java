package org.bloodtorrent.resources;

import com.google.common.io.Files;
import com.google.common.io.InputSupplier;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.apache.commons.lang3.StringUtils;
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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Path("/successStory")
@Produces(MediaType.TEXT_HTML)
public class SuccessStoryResource {
    private final SuccessStoryRepository repository;
    private String UPLOAD_DIR = "/upload";

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
    @Path("/list")
	public SuccessStoryView listSuccessStory() {
		return new SuccessStoryView(repository.getListForSuccessStoriesView());
	}
    @GET
	@UnitOfWork
    @Path("/createView")
	public SuccessStoryView viewSuccessStoryEditor() {
		return new SuccessStoryView();
	}

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/create")
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
            final String outputPath = UPLOAD_DIR + File.separator + id + "-" + content.getFileName();
            String root = getClass().getResource("/").getPath();
            saveFile(root, outputPath, resource);
            story.setThumbnailPath(outputPath);
            story.setVisualResourcePath(outputPath);
        }
        repository.insert(story);

        return new SuccessStoryView(repository.getListForSuccessStoriesView());
    }

    void saveFile(String root, String outputPath, final InputStream resource) throws IOException {
        File uploadDir = new File(root + File.separator + UPLOAD_DIR);
        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }
        Files.copy(new InputSupplier<InputStream>() {
            public InputStream getInput() throws IOException {
                return resource;
            }
        }, new File(root + outputPath));
    }
}
