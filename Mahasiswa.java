

public class Mahasiswa 
{
    private String nim;
    private String nama;
    private String prodi;
    private int semester;
    private String email;
    private String nohp;

    public Mahasiswa(String nim, String nama, String prodi, int smstr, String email, String nohp)
    {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
        this.semester = smstr;
        this.email = email;
        this.nohp = nohp;
    }
    // ini getter
    public String getNim(){return nim;}
    public String getNama(){return nama;}
    public String getProdi(){return prodi;}
    public int getSmstr(){return semester;}
    public String getEmail(){return email;}
    public String getNohp(){return nohp;}

    public void getDataMahasiwa()
    {
        System.out.println("NIM : "+nim);
        System.out.println("Nama : "+nama);
        System.out.println("Prodi : "+prodi);
        System.out.println("Semester : "+semester);
        System.out.println("Email : "+email);
        System.out.println("No HP : "+nohp);
    }
}