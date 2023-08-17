import com.example.petabencana.domain.models.Province

class ProvinceData {

    fun dataProvince(): ArrayList<Province> {
        val data: ArrayList<Province> = ArrayList()
        data.add(Province("ID-AC", "Aceh"))
        data.add(Province("ID-BA", "Bali"))
        data.add(Province("ID-BB", "Kep Bangka Belitung"))
        data.add(Province("ID-BT", "Banten"))
        data.add(Province("ID-BE", "Bengkulu"))
        data.add(Province("ID-JT", "Jawa Tengah"))
        data.add(Province("ID-KT", "Kalimantan Tengah"))
        data.add(Province("ID-ST", "Sulawesi Tengah"))
        data.add(Province("ID-JI", "Jawa Timur"))
        data.add(Province("ID-KI", "Kalimantan Timur"))
        data.add(Province("ID-NT", "Nusa Tenggara Timur"))
        data.add(Province("ID-GO", "Gorontalo"))
        data.add(Province("ID-JK", "DKI Jakarta"))
        data.add(Province("ID-JA", "Jambi"))
        data.add(Province("ID-LA", "Lampung"))
        data.add(Province("ID-MA", "Maluku"))
        data.add(Province("ID-KU", "Kalimantan Utara"))
        data.add(Province("ID-MU", "Maluku Utara"))
        data.add(Province("ID-SA", "Sulawesi Utara"))
        data.add(Province("ID-SU", "Sumatera Utara"))
        data.add(Province("ID-PA", "Papua"))
        data.add(Province("ID-RI", "Riau"))
        data.add(Province("ID-KR", "Kepulauan Riau"))
        data.add(Province("ID-SG", "Sulawesi Tenggara"))
        data.add(Province("ID-KS", "Kalimantan Selatan"))
        data.add(Province("ID-SN", "Sulawesi Tenggara"))
        data.add(Province("ID-SS", "Sumatera Selatan"))
        data.add(Province("ID-YO", "DI Yogyakarta"))
        data.add(Province("ID-JB", "Jawa Barat"))
        data.add(Province("ID-KB", "Kalimantan Barat"))
        data.add(Province("ID-NB", "Nusa Tenggara Barat"))
        data.add(Province("ID-PB", "Papua Barat"))
        data.add(Province("ID-SR", "Sulawesi Barat"))
        data.add(Province("ID-SB", "Sumatera Barat"))
        return data
    }
}