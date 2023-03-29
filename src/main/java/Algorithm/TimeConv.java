package Algorithm;

public class TimeConv
{
    public long sec;

    public TimeConv(long hour, long min, long sec)
    {
        this.sec = (hour*3600) + (min*60) + sec;
    }

    public TimeConv(TimeConv time)
    {
        sec = time.sec;
    }

    @Override
    public String toString()
    {
        long hours = this.sec / 3600;
        long minutes = this.sec % 3600 / 60;
        long seconds = this.sec % 60;

        return hours + ":" + minutes + ":" + seconds;
    }
}
