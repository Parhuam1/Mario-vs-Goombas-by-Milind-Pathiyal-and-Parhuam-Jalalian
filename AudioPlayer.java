


import javax.sound.sampled.*;

public class AudioPlayer
{
    private Clip clip;
    //Pre:String s is valid
    //Post:grabs .wav file using given id string(string s)
    public AudioPlayer(String s)
    {
        try{
            AudioInputStream a = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
            AudioFormat baseFormat = a.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 
            baseFormat.getSampleRate(),
            16,
            baseFormat.getChannels(),
            baseFormat.getChannels() * 2,
            baseFormat.getSampleRate(),
            false);
            AudioInputStream b = AudioSystem.getAudioInputStream(decodeFormat, a);
            clip = AudioSystem.getClip();
            clip.open(b);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    //Pre:None
    //Post:plays music/sound
    public void play()
    {
        if(clip == null)      
            return;
        stop();
        clip.setFramePosition(0);
        clip.start();
    }
    //Pre:None
    //Post:pauses music/sound
    public void stop()
    {
        if(clip.isRunning())
            clip.stop();
    }
    //Pre:None
    //Post:Closes clip
    public void close()
    {
        stop();
        clip.close();
    }
}
