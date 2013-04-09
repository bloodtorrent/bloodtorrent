package org.bloodtorrent.util.exception;

import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 4/9/13
 * Time: 10:49 AM
 * To change this template use File | Settings | File Templates.
 */
@NoArgsConstructor
public class AmbiguousException extends RuntimeException {
    public AmbiguousException(String msg) {
        super(msg);
    }
    public AmbiguousException(Throwable cause) {
        super(cause);
    }
}
