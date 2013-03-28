package org.bloodtorrent.resources;

import com.yammer.dropwizard.hibernate.UnitOfWork;
import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.repository.SuccessStoryRepository;
import org.bloodtorrent.view.SuccessStoryView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

//@Path("/successStory")
@Path("/successStory/{id}")
public class SuccessStoryResource {
    private final SuccessStoryRepository repository;

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

        if (list.size() > SuccessStory.MAXIMUM_SHOWN_STORY_ON_MAIN_PAGE) {
            throw new IllegalDataException("At most " + SuccessStory.MAXIMUM_SHOWN_STORY_ON_MAIN_PAGE + " Success Stories should be shown.");
        }

        return list;
    }

	public SuccessStory get(String id) {
		return repository.get(id);
	}

	@GET
	@UnitOfWork
	public SuccessStoryView getSuccessStory(@PathParam("id") String id) {
		SuccessStory successStory = repository.get(id);
		return new SuccessStoryView(successStory);
	}
}
