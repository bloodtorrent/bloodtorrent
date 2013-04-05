package org.bloodtorrent.util;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: sds
 * Date: 4/5/13
 * Time: 11:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileHandlerTest {
    @Test(expected = FileNotFoundException.class)
    public void shouldThrowExceptionWhenAskedToOpenFileThatDoesNotExist() throws FileNotFoundException {
        FileHandler fileHandler = new FileHandler();
        File file = fileHandler.open("/some/file/that/does/not/exist");
    }

    @Test
    public void shouldCreateNewFile() throws Exception {
        FileHandler fileHandler = new FileHandler();
        File file = mock(File.class);
        assertThat(fileHandler.save(file), is(true));
        verify(file).createNewFile();
    }

    @Test
    public void shouldCreateNewFolderIfItDoesNotExist() throws Exception {
        FileHandler fileHandler = new FileHandler();
        File mockFolder = mock(File.class);
        when(mockFolder.exists()).thenReturn(false);
        fileHandler.mkdir(mockFolder);
        verify(mockFolder).mkdir();
    }

    @Test
    public void shouldNotCreateFolderIfItAlreadyExists() throws Exception {
        FileHandler fileHandler = new FileHandler();
        File mockFolder = mock(File.class);
        when(mockFolder.exists()).thenReturn(true);
        fileHandler.mkdir(mockFolder);
        verify(mockFolder, never()).mkdir();
    }
}
