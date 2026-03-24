
class LombaAkademik extends Lomba
{
    String level_kesulitan;
    String materi_ujian;

    public LombaAkademik(String idlomba, String namalomba, String bidang, String tglLomba,int kuota, String sulit,String materi)
    {
        super(idlomba,namalomba,bidang,tglLomba,kuota);
        this.level_kesulitan = sulit;
        this.materi_ujian = materi;
    }

    // method 1
    public void tampilMatUjian()
    {
        System.out.println("Materi Ujian : "+materi_ujian);
        System.out.println("Level Kesulitan : "+level_kesulitan);
    }

    //method 2
    @Override 
    public void tampilInformasi()
    {
        super.tampilInformasi();
        System.out.println("Level Kesulitan : "+level_kesulitan);
        System.out.println("Materi Ujian : "+materi_ujian);
    }
}