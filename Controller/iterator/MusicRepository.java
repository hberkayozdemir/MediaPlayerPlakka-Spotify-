package Controller.iterator;

public class MusicRepository implements Container {
    public String names[] = {"sfx/lose_your_self.mp3" , "sfx/kotu_eleman.mp3" ,"sfx/gokyuzu.mp3" , "sfx/bazı_zamanlar.mp3" , "sfx/yar_agladi_ben_agladim.mp3"};

    @Override
    public Iterator getIterator() {
        return new NameIterator();
    }

    private class NameIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {
            if(index < names.length){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                return names[index++];
            }
            return null;
        }
    }
}