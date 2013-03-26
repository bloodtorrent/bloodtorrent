package org.bloodtorrent.resources;

import org.bloodtorrent.dto.CatchPhrase;
import org.bloodtorrent.repository.CatchPhraseRepository;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 3/26/13
 * Time: 2:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class CatchPhraseResource {

    private CatchPhraseRepository repository;

    public CatchPhraseResource(CatchPhraseRepository repository) {
        this.repository = repository;
    }


    public CatchPhrase get() {
        return repository.get();
    }
}
