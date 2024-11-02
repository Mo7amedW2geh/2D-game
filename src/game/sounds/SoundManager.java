package game.sounds;

import game.core.Game;

public class SoundManager {

    Game game;

    public SoundManager(Game game){
        this.game = game;
    }

    public void playMusic(int index){
        game.sound.setFile(index);
        game.sound.play();
        game.sound.loop();
    }

    public void playSoundEffect(int index){
        game.sound.setFile(index);
        game.sound.play();
    }

    public void stopMusic(){
        game.sound.stop();
    }
}
