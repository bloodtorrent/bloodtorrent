package org.bloodtorrent.resources;

import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.repository.SuccessStoryRepository;

import javax.ws.rs.Path;
import java.util.List;

public class SuccessStoryResource {
    private final SuccessStoryRepository repository;

    public SuccessStoryResource(SuccessStoryRepository repository) {
        this.repository = repository;
    }

    /**
     *  Get at most 3 Success Stories. This will be used for being shown on main page.
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
}
