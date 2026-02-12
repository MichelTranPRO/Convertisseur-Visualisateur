import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public class FileTraitement {
    
    private File file;
    private DataInputStream dataInput;
    public byte[] header;
    public FileTraitement (File file){
        this.file=file;
        this.dataInput = new DataInputStream(new FileInputStream(file));

    }
}
