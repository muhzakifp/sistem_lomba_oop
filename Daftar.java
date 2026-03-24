import java.util.ArrayList;;

class Daftar
{
    private Mahasiswa mhs;
    private Lomba lmb;
    private String idPendaftar;
    private String tanggalDaftar;
    private String status;

    public Daftar (Mahasiswa mhs, Lomba lmb, String iddaftar, String tgldaftar)
    {
        this.mhs = mhs;
        this.lmb = lmb;
        this.idPendaftar = "DF-"+iddaftar+"-LMB";
        this.tanggalDaftar = tgldaftar;
        this.status = "";
    }

    // ini getter
    public String getiddaftar() {return idPendaftar;}
    public String gettgldaftar() {return tanggalDaftar;}
    public String getstatus() {return status;}
    public Mahasiswa getclassMahasiswa() {return mhs;}
    public Lomba getclassLomba() {return lmb;}

    // method 1
    public void prosesPendaftaran(Lomba l)
    {
        System.out.println("Memproses pendaftaran untukk ID: "+idPendaftar);
        if(l.cekKuota())
        {
            l.tmbhPendaftar();
            status = "Belum disetujui";
            System.out.println("Pendaftaran berhasil diproses !");
        }
        else
        {
            status = "Gagal - Kuota Penuh !";
        }
    }

    // method 2
    public static void syaratDaftar()
    {
        System.out.println("1. Mahasiswa Aktif di PTN/PTS");
        System.out.println("2. Sehat jasmani dan rohani");
        System.out.println("3. Semester 1-4");
        System.out.println("4. Dapat izin dari dosen wali");
    }

    // method 3
    public void updateStatus(String status_baru)
    {
        this.status = status_baru;
        System.out.println("Status peserta pendaftaran atas nama "+mhs.getNama()+" telah "+status);   
    }

    // method 4
    public static boolean cekDaftar(ArrayList<Daftar> cek,Lomba lmb, Mahasiswa mhs)
    {
        for(Daftar d :  cek)
        {
            if(d.getclassMahasiswa().getNim().equals(mhs.getNim()) && d.getclassLomba().getId().equals(lmb.getId()))
            {
                return true;
            }
        }
        return false;
    }

    // method 5
    public void tampilBuktiDaftar()
    {
        System.out.println("\n---> BUKTI PENDAFTARAN "+mhs.getNama().toUpperCase()+" <---");
        System.out.println("ID pendaftar :"+idPendaftar);
        System.out.println("Tanggal Daftar :"+tanggalDaftar);
        System.out.println("Nama Mahasiswa : "+mhs.getNama());
        System.out.println("Prodi : "+mhs.getProdi());
        System.out.println("Nomor HP : "+mhs.getNohp());
        System.out.println("Kampus Asal : "+mhs.getKampusAsal());
        System.out.println("Lomba diikuti : "+lmb.getNamaLomba());
        System.out.println("Bidang Lomba : "+lmb.getBidang());        
        System.out.println("Tanggal Lomba : "+lmb.getTanggal());        
        System.out.println("Status :"+status);
    }

}