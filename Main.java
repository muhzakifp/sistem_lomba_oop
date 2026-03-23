import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    static ArrayList<Mahasiswa> DaftarMahasiswa = new ArrayList<>();
    static ArrayList<Lomba> DaftarLomba = new ArrayList<>();
    static ArrayList<Daftar> DaftarRegistrasi = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        DaftarLomba.add(new Lomba("LO2624001", "Lomba pengembangan Website", "Teknologi", "1-Januari-2026", 20));
        DaftarLomba.add(new Lomba("LO2624002", "Lomba robotik", "Teknologi", "12-Januari-2026", 20));
        DaftarLomba.add(new Lomba("LO2624003", "catur", "Olahraga", "11-Januari-2026", 10));
        DaftarLomba.add(new Lomba("LO2624004", "Futsal", "Olahraga", "15-Februari-2026", 15));
        DaftarLomba.add(new Lomba("LO2624005", "Desain Poster", "Seni dan Kreativitas", "2-Februari-2026", 5));
        DaftarLomba.add(new Lomba("LO2624006", "Hafidz", "Keagamaan", "5-Februari-2026", 5));
        DaftarLomba.add(new Lomba("LO2624007", "Karya Ilmiah", "Penelitian", "10-Januari-2026", 5));
        DaftarLomba.add(new LombaAkademik("LO2624008", "Olimpiade Matematika", "Akademik", "12-januari-2026", 5, "Medium", "Aljabar dan Kalkulus"));

        int opsi;
        do
        {
            System.out.println("! Syarat Pendaftaran !");
            Daftar d = new Daftar("" ,"");
            d.syaratDaftar();

            System.out.println("\n---> SISTEM PENDAFTARAN LOMBA <---");
            System.out.println("1.Daftarkan Mahasiswa");
            System.out.println("2.Daftarkan Lomba");
            System.out.println("3.Lihat Bukti Pendaftaran");
            System.out.println("4.Uodate Status Pendaftaran");
            System.out.println("5.Keluar");
            System.out.print("Pilih menu : ");
            opsi = input.nextInt();
            input.nextLine();

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
    public static void TambahMahasiswa()
    {
        String nim, nama, prodi,email,nohp;
        int smstr;

        System.out.println("---> DAFTARKAN MAHASISWA <---");
        System.out.print("NIM : ");
        nim = input.nextLine();
        for (Mahasiswa mhs  : DaftarMahasiswa)
        {
            if(mhs.getNim().equals(nim))
            {
                System.out.println("NIM sudah adaa !");
            }
        }
        System.out.print("Nama : ");
        nama = input.nextLine();
        System.out.print("Prodi : ");
        prodi = input.nextLine();
        System.out.print("Semester : ");
        smstr = input.nextInt();
        input.nextLine();
        if (smstr<=0 || smstr>=5 )
        {
            System.out.println("--- Semester tidak valid ! ---");
            System.out.println("--- Pendaftar wajib Mahasiswa Semester 1-4 ---");
            return;
        }
        System.out.print("Email : ");
        email = input.nextLine().trim();
        if (email.endsWith("@gmail.com"))
        {
            System.out.print("Nomor HP aktif : ");
            nohp = input.nextLine();

            Mahasiswa mhs = new Mahasiswa(nim, nama, prodi, smstr, email, nohp);
            DaftarMahasiswa.add(mhs);
            System.out.println("Mahasisswa berhasil ditambahkan ! ");
        }
        else
        {
            System.out.println("Email tidak valid !");
            return;
        }
        
    }
    
    public static void DaftarLomba()
    {
        ArrayList<Lomba> urutan_lomba = new ArrayList<>();
        int no = 1;
        if(DaftarMahasiswa.isEmpty())
        {
            System.out.println("Belum ada data Mahasiswa ! ");
            return;
        }
        System.out.println("---> DAFTAR LOMBA TERSEDIA <---");
        System.out.println("[--LOMBA UMUM--]");
        for (Lomba l : DaftarLomba)
        {
            if(!(l instanceof LombaAkademik))
            {
                System.out.println((no)+"."+l.getId()+"-"+l.getNamaLomba()+"-"+l.getBidang());
                urutan_lomba.add(l);
                no++;
            }
        }
        System.out.println("[--LOMBA AKADEMIK--]");
        for(Lomba l : DaftarLomba)
        {
            if(l instanceof LombaAkademik)
            {
                System.out.println((no)+"."+l.getId()+"-"+l.getNamaLomba()+"-"+l.getBidang());
                urutan_lomba.add(l);
                no++;
            }
        }

        System.out.print("Pilih lomba : ");
        int pilih_lomba =  input.nextInt();
        input.nextLine();
        
        if (pilih_lomba >=1 && pilih_lomba <= urutan_lomba.size())
        {
            Lomba lomba_terpilih = urutan_lomba.get(pilih_lomba-1);

            System.out.println("--- ANDA MEMILIH LOMBA ---");
            lomba_terpilih.tampilInformasi();
            System.out.println("Kuota Tersedia : "+(lomba_terpilih.getKuota()-lomba_terpilih.getJmlhDaftar()));
            
            System.out.print("Apakah yakin ingin mendaftar? (Y/N) : ");
            String konf = input.nextLine();
            if (konf.equalsIgnoreCase("y"))
                {
                    ArrayList<Mahasiswa> urutan_mahasiswa = new ArrayList<>();

                    int nomhs = 1;
                    for (Mahasiswa mhs : DaftarMahasiswa)
                    {
                        System.out.println((nomhs)+"."+mhs.getNim()+"-"+mhs.getNama()+"-"+mhs.getProdi()+"-"+mhs.getSmstr());
                        urutan_mahasiswa.add(mhs);
                        nomhs++;
                    }
                    System.out.print("Masukkan index peserta : ");
                    int index = input.nextInt();
                    input.nextLine();

                    if (index >=1 && index <= urutan_mahasiswa.size())
                    {
                        Mahasiswa mhs_terpilih = urutan_mahasiswa.get(index-1);
                        System.out.println("ADA MEMILIH MAHASISWA");
                        mhs_terpilih.getDataMahasiwa();
                        System.out.print("Tanggal Daftar (DD-MM-YYY) : ");
                        String tgl = input.nextLine();
                        
                        Daftar daftar = new Daftar(mhs_terpilih.getNim(),tgl);
                        daftar.prosesPendaftaran(lomba_terpilih); 
                        DaftarRegistrasi.add(daftar);
                        
                        if(lomba_terpilih instanceof LombaAkademik)
                            {
                                System.out.println("---> INFORMASI LOMBA AKADEMIK <---");
                                ((LombaAkademik) lomba_terpilih).tampilMatUjian();
                            }
                        System.out.println("Banyak Pendaftar : "+lomba_terpilih.tmbhPendaftar());           
                    }
                    else {System.out.println("Nomor urut tidak valid ! ");}
                }    
            else 
                {
                    System.out.println("Pendaftaran dibatalkan");
                    return;
                }
        }
                    
        else
        {
            System.out.println("Pilihan tidak valid !");
        }
    }

    public static void LihatBuktiDaftar()
    {
        
        System.out.println("---> BUKTI PENDAFTARAN LOMBA <---");
        if(DaftarRegistrasi.isEmpty())
        {
            System.out.println("Belum ada Pendaftaran");
            return;
        }
        else
        {
            for (Daftar d : DaftarRegistrasi)
            {
                d.tampilBuktiDaftar();
            }
        }
    }

    public static void UpdateStatus()
    {
        if(DaftarRegistrasi.isEmpty())
        {
            System.out.println("Belum ada pendaftaran");
            return;
        }
        else
        {
            System.out.println("---> UPDATE STATUS PENDAFTARAN <---");
            System.out.print("Masukan ID pendaftar (NIM) : ");
            String id = input.nextLine();

            boolean ditemukan = false;
            for (Daftar d : DaftarRegistrasi)
            {
                System.out.print("Status baru (Disetujui/Ditolak) : ");
                String statusBaru = input.nextLine();
                d.updateStatus(statusBaru);
                ditemukan = true;
                break;
            }
            if(!ditemukan) {System.out.println("Pendaftaran dengan nim "+id+" tidak ditemukan ! ");}
        }
    }
}