package Controller.PrototypePattern;

public class Images extends  Path{

    public Images(){
        absolutepath = "../src/images/";
    }


    String getPath() {
        return getAbsolutepath();
    }
}

