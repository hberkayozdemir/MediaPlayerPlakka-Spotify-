package Controller.PrototypePattern;

public class Gui extends Path {


    public Gui(){
        absolutepath = "/View/GUI/";
    }

    @Override
    String getPath() {
        return getAbsolutepath();
    }
}
