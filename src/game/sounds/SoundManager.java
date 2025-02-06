package game.sounds;

public class SoundManager {

    Sound music = new Sound();
    Sound soundEffect = new Sound();

    public void playMusic(int index){
        music.setFile(index);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSoundEffect(int index){
        soundEffect.setFile(index);
        soundEffect.play();
    }


}
