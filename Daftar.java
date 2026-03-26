import java.util.ArrayList;;

class Daftar
{
    private Mahasiswa mhs;
    private Lomba lmb;
    private String idPendaftar, tanggalDaftar,status;
 
    Daftar(Mahasiswa mhs, Lomba lmb, String iddaftar, String tgldaftar)
    {
        this.mhs = mhs;
        this.lmb = lmb;
        this.idPendaftar = "DF-"+iddaftar+"-LMB";
        this.tanggalDaftar = tgldaftar;
        this.status = "";
    }

    // ini getter
    String getiddaftar() {return idPendaftar;}   String gettgldaftar(){return tanggalDaftar;}
    String getstatus() {return status;}  Mahasiswa getclassMahasiswa() {return mhs;} 
    Lomba getclassLomba() {return lmb;}

    // method 1 untuk memperoses status pendaftaran
    void prosesPendaftaran(Lomba l)
    {
        System.out.println("Memproses pendaftaran untukk ID: "+idPendaftar);
        if(l.cekKuota())
        {
            l.tmbhPendaftar();
            status = "Belum disetujui";
            System.out.println("Pendaftaran berhasil diproses !");
        }
        else { status = "Gagal - Kuota Penuh !"; }
    }

    // method 2 untuk memberi informasi rules pendaftaran lomba
    static void syaratDaftar()
    {
        System.out.println("1. Mahasiswa Aktif di PTN/PTS");
        System.out.println("2. Sehat jasmani dan rohani");
        System.out.println("3. Semester 1-4");
        System.out.println("4. Dapat izin dari dosen wali");
    }

    // method 3 untuk mengupdate status
    void updateStatus(String status_baru)
    {
        this.status = status_baru;
        System.out.println("Status peserta dengan ID pendaftaran "+idPendaftar+" atas nama "+mhs.getNama()+" telah "+status);   
    }

    // method 4 untuk cekdaftar untuk menghindari pendaftaran nama lomba yang sama
    static boolean cekDaftar(ArrayList<Daftar> cek,Lomba lmb, Mahasiswa mhs)
    {
        for(Daftar d :  cek)
        {
            if(d.getclassMahasiswa().getNim().equals(mhs.getNim()) && d.getclassLomba().getId().equals(lmb.getId()))
            { return true; }
        }
        return false;
    }

    // method 5 tampilkan informasi pendaftaran
    void tampilBuktiDaftar()
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