import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    // ARRAY LIST SETIAP CLASS
    static ArrayList<Mahasiswa> DaftarMahasiswa = new ArrayList<>(); //untuk menampung setiap data mahasiswa
    static ArrayList<Lomba> DaftarLomba = new ArrayList<>(); // untuk menampung setiap data perlombaan yang tersedia
    static ArrayList<Daftar> DaftarPeserta = new ArrayList<>(); // untuk memnampung data mahasiswa yang resmi jadi peserta lomba
    static ArrayList<Daftar> CalonPeserta = new ArrayList<>(); // untuk menampung data sementara  mahasiswa untuk menunggu pemrosesesan update status
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        // DATA PERLOMBAAN
        DaftarLomba.add(new Lomba("LO2624001", "Ngoding", "Teknologi", "1-Januari-2026", 20));
        DaftarLomba.add(new Lomba("LO2624002", "Futsal", "Olahraga", "15-Februari-2026", 15));
        DaftarLomba.add(new Lomba("LO2624004", "Desain Poster", "Seni dan Kreativitas", "2-Februari-2026", 1));
        DaftarLomba.add(new Lomba("LO2624005", "Karya Ilmiah", "Penelitian", "10-Januari-2026", 5));
        DaftarLomba.add(new LombaAkademik("LO2624006", "Olimpiade Matematika", "Akademik", "12-januari-2026", 5, "Medium", "Aljabar dan Kalkulus"));

        int opsi;
        do
        {
            // SYARAT PENDAFTARAN
            Daftar.syaratDaftar();

            // FITUR MENU
            System.out.println("\n---> SISTEM PENDAFTARAN LOMBA <---");
            System.out.println("1.Tambah Mahasiswa\n2.Daftarkan Lomba\n3.Lihat Bukti Pendaftaran\n4.Update Status Pendaftaran\n5.Keluar");
            System.out.print("Pilih menu : "); opsi = input.nextInt(); input.nextLine();

            // PERCBANGAN SWITCH
            switch (opsi)
            {
                case 1: TambahMahasiswa(); break; 
                case 2: DaftarLomba(); break;
                case 3: LihatBuktiDaftar(); break;
                case 4: UpdateStatus(); break;
                case 5: System.out.println("Anda telah keluar"); break;
                default : System.out.println("Pilihan tidak valid !"); 
            }
        }while (opsi !=5);
    }

    // FUNCTION TAMBAH MAHASISWA
    public static void TambahMahasiswa()
    {
        String nim, nama, prodi,kampus,nohp; 
        int smstr;

        System.out.println("\n---> TAMBAH MAHASISWA <---");
        System.out.print("NIM : "); nim = input.nextLine();

        // untuk mengecek isi objek mhs yang terdapat pada DaftarMahasiswa sekaligus mengecek nim untuk mengindari duplikat 
        for (Mahasiswa mhs  : DaftarMahasiswa) { if(mhs.getNim().equals(nim)) {System.out.println("NIM sudah adaa !"); return;} }

        System.out.print("Nama : "); nama = input.nextLine();
        System.out.print("Prodi : "); prodi = input.nextLine();
        System.out.print("Semester : "); smstr = input.nextInt(); input.nextLine();
        if (smstr<=0 || smstr>4 )
        { System.out.println("--- Semester tidak valid ! ---\n--- Pendaftar wajib Mahasiswa Semester 1-4 ---");return;}

        System.out.print("Kampus Asal : "); kampus = input.nextLine();
        System.out.print("Nomor HP aktif : "); nohp = input.nextLine();

        Mahasiswa mhs = new Mahasiswa(nim, nama, prodi, smstr, kampus, nohp); // objek mhs dibuat 
        DaftarMahasiswa.add(mhs); // data objek akan tersimpan di ArrayList DaftarMahasiswa
        mhs.getDataMahasiwa(); // menampilkan data mahasiswa
        System.out.println("                 Mahasiswa berhasil ditambahkan !              ");
        input.nextLine();
    }
    
    // FUNCTION DAFTAR LOMBA
    public static void DaftarLomba()
    {
        // pengecekan kondisi jika ArrayList DaftarMahasiswa kosong
        if(DaftarMahasiswa.isEmpty()) { System.out.println("Belum ada data Mahasiswa ! "); return; }

        System.out.println("\n---> DAFTAR LOMBA TERSEDIA <---"); 
        for (int i=0;i<DaftarLomba.size();i++) 
        {
              // menampilkan urutan informasi lomba umum
            System.out.println((i+1)+". Lomba "+DaftarLomba.get(i).getNamaLomba()); 
            DaftarLomba.get(i).tampilInformasi();
        }
        // input untuk pemilihan list lomba 
        System.out.print("Pilih informasi lomba (contoh :1) : "); int pil_lmb =  input.nextInt(); input.nextLine();
        
        if (pil_lmb >=1 && pil_lmb <= DaftarLomba.size()) 
        {
            Lomba lomba_terpilih = DaftarLomba.get(pil_lmb-1);
          
            System.out.println("\n--- INFORMASI LOMBA "+lomba_terpilih.getNamaLomba().toUpperCase() +" ---");
            lomba_terpilih.tampilInformasi();
            
            System.out.print("Apakah yakin ingin mendaftar?  (Y/N) : "); String konf = input.nextLine();
            if (konf.equalsIgnoreCase("y"))
                {
                    // cek kondisi kuota lomba
                    if (!(lomba_terpilih.cekKuota())) {lomba_terpilih.tmbhPendaftar(); return;} //  kalo kuota penuh maka diarahkan ke if
                    else // kalo  belum penuh maka diarahkan ke kondisi else
                    {
                        // input nim mahasiswa untuk pendaftaran lomba
                        System.out.print("Masukkan NIM Mahasiswa  : ");
                        String nim =  input.nextLine();

                        Mahasiswa mahasiswa = null; 
                        for (Mahasiswa mhs : DaftarMahasiswa) // cek nim mahasiswa apakah sudah terdaftar
                        {if(mhs.getNim().equals(nim)) { mahasiswa = mhs; break; }}

                        if (mahasiswa != null)
                        {
                            if(Daftar.cekDaftar(DaftarPeserta, lomba_terpilih, mahasiswa) || Daftar.cekDaftar(CalonPeserta, lomba_terpilih, mahasiswa))
                            {
                                System.out.println("Pendaftaran Gagal - Peserta atas nama "+mahasiswa.getNama()+" sudah terdaftar di lomba yang sama ! ");
                                return;
                            }
                            else
                            {
                                System.out.print("Tanggal Daftar (TGL-BLN-THN) : "); String tgl = input.nextLine();
                                Daftar proses = new Daftar(mahasiswa, lomba_terpilih, mahasiswa.getNim(),tgl);
                                CalonPeserta.add(proses);
                                proses.prosesPendaftaran(lomba_terpilih);
                            }
                        }
                        else {System.out.println("NIM "+nim+" tidak terdaftar ! "); return;}        
                    } 
                }
            else {System.out.println("Pendaftaran lomba di batalkan"); return;}  
        }
        else {System.out.println("Pemilihan nomot urut lomba tidak valid ! "); return;}   
    }
                   
    public static void LihatBuktiDaftar()
    {
        System.out.println("\n---------------------------> DATA CALON PESERTA LOMBA <----------------------");
        if (CalonPeserta.isEmpty()) {System.out.println("Data calon peserta lomba belum ada !");}
        for(Daftar calon : CalonPeserta) { calon.tampilBuktiDaftar(); }

        System.out.println("\n---------------------------> DATA PESERTA LOMBA <----------------------");
        if (DaftarPeserta.isEmpty()) {System.out.println("Data peserta lomba belum ada !");}
        for (Daftar peserta : DaftarPeserta) { peserta.tampilBuktiDaftar();}
    }
    
    public static void UpdateStatus()
    {
        System.out.println("\n---> UPDATE STATUS PENDAFTARAN <---");

        if(CalonPeserta.isEmpty()) {System.out.println("Belum ada pendaftaran");return;}

        else
        {
            for(int i=0;i<CalonPeserta.size();i++) 
                { 
                    System.out.println((i+1)+". "+CalonPeserta.get(i).getiddaftar()+" | "+CalonPeserta.get(i).getclassMahasiswa().getNama()+
                " | "+CalonPeserta.get(i).getclassLomba().getNamaLomba());
                }
            System.out.print("Masukan nomor urut pendaftar (contoh : 1) : "); 
            int index_pendaftar = input.nextInt(); input.nextLine();

            if (index_pendaftar >=1 && index_pendaftar <= CalonPeserta.size())
            {
                Daftar pendaftar_terpilih = CalonPeserta.get(index_pendaftar-1);
                Lomba lmb = pendaftar_terpilih.getclassLomba();

                System.out.println("Anda memilih peserta pendaftaran atas nama "+pendaftar_terpilih.getclassMahasiswa().getNama());
                pendaftar_terpilih.tampilBuktiDaftar();
                System.out.print("Status baru (Disetujui/Ditolak) : "); String statusBaru = input.nextLine();
                if (statusBaru.equalsIgnoreCase("ditolak"))
                {   
                    pendaftar_terpilih.updateStatus(statusBaru);
                    lmb.kurangiDaftar();
                    CalonPeserta.remove(pendaftar_terpilih);
                    System.out.println("ID pendaftar "+pendaftar_terpilih.getiddaftar()+" atas nama "+pendaftar_terpilih.getclassMahasiswa().getNama()+" telah di "+statusBaru+" !");
                }             
                else if (statusBaru.equalsIgnoreCase("disetujui"))
                {
                    pendaftar_terpilih.updateStatus(statusBaru);
                    lmb.tmbhPendaftar();
                    CalonPeserta.remove(pendaftar_terpilih);
                    DaftarPeserta.add(pendaftar_terpilih);
                    System.out.println("ID pendaftar "+pendaftar_terpilih.getiddaftar()+" atas nama "+pendaftar_terpilih.getclassMahasiswa().getNama()+" telah di "+statusBaru+" !");
                }
                else { System.out.println("Update Status tidak valid ! "); return;}
            }
            else { System.out.println("nomor urut pendaftar tidak valid !"); return; }    
        } 
    }
}