package org.bloodtorrent.resources;

import org.bloodtorrent.IllegalDataException;
import org.bloodtorrent.dto.SuccessStory;
import org.bloodtorrent.repository.SuccessStoryRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 13. 3. 22
 * Time: 오전 11:40
 * To change this template use File | Settings | File Templates.
 */
public class SuccessStoryResource {
    private final SuccessStoryRepository repository;

    public SuccessStoryResource(SuccessStoryRepository repository) {
        this.repository = repository;
    }

    /**
     *  Get at most 3 Success Stories. This will be used for being shown on main page.
     * @return
     * @throws IllegalDataException Thrown unless the number of shown success stories is between 0 and 3.
     */
    public List<SuccessStory> getSuccessStoriesBriefly() throws IllegalDataException {
        List<SuccessStory> list = repository.list();

        if (list.size() > SuccessStory.MAXIMUM_SHOWN_STORY_ON_MAIN_PAGE) {
            throw new IllegalDataException("At most " + SuccessStory.MAXIMUM_SHOWN_STORY_ON_MAIN_PAGE + " Success Stories should be shown.");
        }

        shortenDescription(list);
        return list;
    }

    /**
     * Shorten description if it is longer than 100 characters.
     * @param list
     */
    private void shortenDescription(List<SuccessStory> list) {
        for (SuccessStory successStory : list) {
            String description = successStory.getDescription();
            if(description.length() > 100) {
                successStory.setDescription(description.substring(0, 100) + " ...");
            }
        }
    }
}
