class LombaAkademik extends Lomba
{
    private String level_kesulitan;
    private String materi_ujian;

    LombaAkademik(String idlomba, String namalomba, String bidang, String tglLomba,int kuota, String sulit,String materi)
    {
        super(idlomba,namalomba,bidang,tglLomba,kuota);
        this.level_kesulitan = sulit;
        this.materi_ujian = materi;
    }

    // method 1
    void tampilMatUjian()
    {
        System.out.println("Materi Ujian : "+materi_ujian);
        System.out.println("Level Kesulitan : "+level_kesulitan);
    }

    //method 2 
    void tampilInformasi()
    {
        System.out.println("ID Lomba :  "+getId());
        System.out.println("Nama Lomba :  "+getNamaLomba());
        System.out.println("Bidang :  "+getBidang());
        System.out.println("Tanggal :  "+getTanggal());
        System.out.println("Level Kesulitan : "+level_kesulitan);
        System.out.println("Materi Ujian : "+materi_ujian);
        System.out.println("Pendaftar :"+getJmlhDaftar());
        System.out.println("Kuota : "+(getKuota()-getJmlhDaftar()));
    }
}