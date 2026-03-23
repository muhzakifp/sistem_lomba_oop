
public class LombaAkademik extends Lomba
{
    String level_kesulitan;
    String materi_ujian;

    public LombaAkademik(String idlomba, String namalomba, String bidang, String tglLomba,int kuota, String sulit,String materi)
    {
        super(idlomba,namalomba,bidang,tglLomba,kuota);
        this.level_kesulitan = sulit;
        this.materi_ujian = materi;
    }

    public void hitungSkor(Lomba l)
    {
       System.out.println("Hitung skor lomba"+l.getNamaLomba());
       System.out.println("Level "+level_kesulitan);
    }

    public void tampilMatUjian()
    {
        System.out.println("Materi Ujian : "+materi_ujian);
        System.out.println("Level Kesulitan : "+level_kesulitan);
    }

    @Override 
    public void tampilInformasi()
    {
        super.tampilInformasi();
        System.out.println("Level Kesulitan : "+level_kesulitan);
        System.out.println("Materi Ujian : "+materi_ujian);
    }
}