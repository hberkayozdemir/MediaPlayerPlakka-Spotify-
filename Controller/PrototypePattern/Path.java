package Controller.PrototypePattern;

public abstract class Path implements Cloneable {



    protected String absolutepath;
    private String directory;


    abstract String getPath();

    public String getAbsolutepath() {
        return absolutepath;
    }

    public void setAbsolutepath(String absolutepath) {
        this.absolutepath = absolutepath;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }



    public Object clone(){
    Object clone=null;
        try {
            clone = super.clone();

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();

        }


        return clone;
    }
}
