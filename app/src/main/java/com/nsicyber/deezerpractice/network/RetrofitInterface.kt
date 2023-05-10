package com.nsicyber.deezerpractice.network


import com.google.gson.JsonArray
import com.nsicyber.deezerpractice.models.ArrayAlbumModel
import com.nsicyber.deezerpractice.models.ArrayArtistModel
import com.nsicyber.deezerpractice.models.ArrayGenreModel
import com.nsicyber.deezerpractice.models.GenreModel
import retrofit2.Call
import retrofit2.http.*
//            var BASE_URL = "https://api.deezer.com"
interface RetrofitInterface {


    @GET("/genre")
    fun getAllGenres(): Call<ArrayGenreModel?>

   @GET("/genre/{genreId}/artists")
    fun getGenreArtists(
        @Path(value = "genreId") genreId: Int?
    ): Call<ArrayArtistModel?>

    @GET("/artist/{artistId}/albums")
    fun getArtistAlbums(
        @Path(value = "artistId") artistId: String?
    ): Call<ArrayAlbumModel?>

     @GET("/album/{albumId}")
    fun getAlbum(
        @Path(value = "albumId") albumId: String?
    ): Call<ArrayAlbumModel?>




    data class CreatePageBody(
        val name: String,
        val countryCode: String,
        val navigation: String,
        val viewStyle: String
    )

    @POST("/api/Page/CreatePage")
    fun createPage(@Body createPageBody: CreatePageBody): Call<FragmentResponseModel>


    data class UpdatePageBody(
        val name: String,
        val countryCode: String,
        val navigation: String,
        val viewStyle: String,
        val id: String
    )

    @PUT("/api/Page/UpdatePage")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun updatePage(@Body updatePageBody: UpdatePageBody): Call<FragmentResponseModel>


    @GET("/api/Component/GetAllComponents")
    fun getAllComponents(): Call<FragmentResponseModel?>


    data class CreateComponentBody(val code: String, val description: String, val name: String)

    @POST("/api/Component/CreateComponent")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun createComponent(@Body createComponentBody: CreateComponentBody): Call<FragmentResponseModel>


    data class CreatePageComponentBody(
        val pageId: String,
        val componentId: String,
        val order: Int = 0,
        val properties: String
    )

    @POST("/api/Component/CreatePageComponent")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun createPageComponent(@Body createPageComponentBody: CreatePageComponentBody): Call<FragmentResponseModel>


    //FixedData
    @GET("/api/Event/GetEventInfo")
    fun getEventInfo(@Query("EventId") eventId: Int?,@Query("CityId") cityId: Int?=5): Call<DictionaryResponseModel>


    @GET("/api/Payment/PaymentSummary")
    fun paymentSummary(
        @Query("SeanceId") seanceId: Int?,
        @Query("CinemaBranchId") cinemaBranchId: Int?,
        @Query("TicketTypeIds") ticketTypeIds: List<Int>?,
        @Query("ProductIds") productIds: List<Int>?,
        @Query("CouponCode") couponCode: String?
    ): Call<DictionaryResponseModel>


    @GET("/api/Product/GetProducts")
    fun getProducts(@Query("CinemaBranchId") cinemaBranchId: Int?): Call<ArrayDictionaryResponseModel>


    @GET("/api/Seance/GetSeancesForEvent")
    fun getSeancesForEvent(
        @Query("EventId") eventId: Int?,
        @Query("CinemaBranchId") cinemaBranchId: Int?,
        @Query("Date") date: String?
    ): Call<ArrayDictionaryResponseModel>

    @GET("/api/Seance/GetAllSeancesForEvent")
    fun getAllSeancesForEvent(
        @Query("EventId") eventId: Int?
    ): Call<ArrayDictionaryResponseModel>


    @GET("/api/Seance/GetBranchesForEvent")
    fun getBranchesForEvent(
        @Query("EventId") eventId: Int?,
        @Query("CinemaBranchId") cinemaBranchId: Int? = null,
        @Query("Date") date: String?
    ): Call<ArrayDictionaryResponseModel>


    @GET("/api/Seat/GetSeatList")
    fun getSeats(
        @Query("SeanceId") seanceId: Int?,
        @Query("FromFeed") fromFeed: Boolean?
    ): Call<DictionaryResponseModel>




    @GET("/api/Branch/GetBranchDetail")
    fun getBranchDetail(
        @Query("BranchId") branchId: Int?
    ): Call<DictionaryResponseModel>

    @GET("/api/Branch/GetEvents")
    fun getEvents(
        @Query("BranchId") branchId: Int?=null,
        @Query("Page") page: Int?=null,
        @Query("Date") date: String?=null,
        @Query("PageSize") pageSize: Int?=14,
    ): Call<ArrayDictionaryResponseModel>

    @GET("/api/EventGroup/GetEvents")
    fun getEventsByEventGroup(
        @Query("EventGroupId") eventGroupId: Int?=null,
        @Query("Page") page: Int?=null,
        @Query("PageSize") pageSize: Int?=14,
    ): Call<ArrayDictionaryResponseModel>


    @GET("/api/Event/GetEvents")
    fun getEventsAll(
        @Query("OrganizerType") organizerType: Int?=null,
        @Query("Page") page: Int?=null,
        @Query("PageSize") pageSize: Int?=14,
    ): Call<ArrayDictionaryResponseModel>

    @GET("/api/Event/GetCinemaEventsForNextVisions")
    fun getEventsForNextVisions(
        @Query("Page") page: Int?=null,
        @Query("PageSize") pageSize: Int?=14,
    ): Call<ArrayDictionaryResponseModel>


    @GET("/api/TicketPrice/GetTicketPrice")
    fun getTicketPrice(@Query("SeanceId") seanceId: Int?): Call<ArrayDictionaryResponseModel>


    @GET("/api/TicketType")
    fun ticketType(@Query("seanceId") seanceId: Int?): Call<FragmentResponseModel>


    //Basket
    data class BasketDeleteBody(val seatId: Int = -1, val seanceId: Int = -1, val sessionId: String)

    @HTTP(method = "DELETE", path = "/api/Basket/Delete", hasBody = true)
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun basketDelete(@Body basketDeleteBody: BasketDeleteBody): Call<MapResponseModel>


    data class BasketClearBody(val sessionId: String)

    @HTTP(method = "DELETE", path = "/api/Basket/Clear", hasBody = true)
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun basketClear(@Body basketClearBody: BasketClearBody): Call<MapResponseModel>


    data class BasketAddBody(val seatId: Int = -1, val seanceId: Int = -1, val sessionId: String)

    @POST("/api/Basket/Add")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun basketAdd(@Body basketAddBody: BasketAddBody): Call<MapResponseModel>



    data class AddToBasketNoSeatBody(val count: Int = -1, val seanceId: Int = -1, val sessionId: String)

    @POST("/api/Basket/AddToBasketNoSeat")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun addToBasketNoSeat(@Body basketAddBody: AddToBasketNoSeatBody): Call<MapResponseModel>


    data class RemoveBasketNoSeat(val sessionId: String)

    @HTTP(method = "DELETE", path = "/api/Basket/RemoveBasketNoSeat", hasBody = true)
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun removeBasketNoSeat(@Body basketClearBody: RemoveBasketNoSeat): Call<MapResponseModel>


    //CardRowListComponent
    @GET("/api/CardRowListComponent/CRL001")
    fun getCRL001(
        @QueryMap() map: Map<String, String>? = null,
        //----------------
        @Query("EventGroupId") eventGroupId: Int? = null,
        @Query("BranchId") branchId: Int? = null,
        @Query("OrganizerType") organizerType: Int? = null,
        @Query("DayCount") dayCount: Int? = 14,
        @Query("Type") type: Int? = null,
        @Query("ruleList") ruleList: List<RulesModel>? = null
    ): Call<ArrayDictionaryResponseModel>


    @GET("/api/CardRowListComponent/CRL003")
    fun getCRL003(
        @QueryMap() map: Map<String, String>? = null,
//--------------
        @Query("OrganizerType") organizerType: Int? = null,
        @Query("Type") type: Int? = null,
        @Query("ruleList") ruleList: List<RulesModel>? = null
    ): Call<ArrayDictionaryResponseModel>


    //CardViewComponent
    @GET("/api/CardViewComponent/CV001")
    fun getCV001(
        @QueryMap() map: Map<String, String>? = null,
//--------------
        @Query("FilmIds") filmIds: List<Int>?= null,
        @Query("EventGroupId") eventGroupId: Int?= null,
        @Query("BranchId") branchId: Int?= null,
        @Query("DayCount") dayCount: Int? = null,
        @Query("Type") type: Int?= null,
        @Query("ruleList") ruleList: List<RulesModel>?= null
    ): Call<ArrayDictionaryResponseModel>

    @GET("/api/CardViewComponent/CV003")
    fun getCV003(
        @QueryMap() map: Map<String, String>? = null,
//--------------
        @Query("FilmIds") filmIds: List<Int>?=null,
        @Query("EventGroupId") eventGroupId: Int?=null,
        @Query("BranchId") branchId: Int?=null,
        @Query("DayCount") dayCount: Int?=null,
        @Query("Type") type: Int?=null,
        @Query("ruleList") ruleList: List<RulesModel>?=null
    ): Call<ArrayStringResponseModel>


    @GET("/api/CardViewComponent/CV006")
    fun getCV006(
        @QueryMap() map: Map<String, String>? = null,
//--------------
        @Query("FilmIds") filmIds: List<Int>?= null,
        @Query("EventGroupId") eventGroupId: Int?= null,
        @Query("BranchId") branchId: Int?= null,
        @Query("DayCount") dayCount: Int? = null,
        @Query("Type") type: Int?= null,
        @Query("ruleList") ruleList: List<RulesModel>?= null
    ): Call<ArrayResponseModel>

    //ColumnListComponent
    @GET("/api/ColumnListComponent/CL001")
    fun getCL001(
        @QueryMap() map: Map<String, String>? = null,
//--------------
        @Query("EventIds") eventIds: List<Int>? = null,
        @Query("EventGroupId") eventGroupId: Int? = null,
        @Query("BranchId") branchId: Int? = null,
        @Query("DayCount") dayCount: Int? = null,
        @Query("Type") type: Int? = null,
        @Query("ruleList") ruleList: List<RulesModel>? = null
    ): Call<ArrayDictionaryResponseModel>


    //EventAboutComponent
    @GET("/api/EventAboutComponent/EA001")
    fun getEA001(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>

    @GET("/api/EventAboutComponent/EA002")
    fun getEA002(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>

    @GET("/api/EventAboutComponent/EA003")
    fun getEA003(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>

    @GET("/api/EventAboutComponent/EA004")
    fun getEA004(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>

    @GET("/api/EventAboutComponent/EA005")
    fun getEA005(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>

    //EventDetailComponent
    @GET("/api/EventDetailComponent/ED001")
    fun getED001(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>

    @GET("/api/EventDetailComponent/ED002")
    fun getED002(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>


    @GET("/api/EventDetailComponent/ED003")
    fun getED003(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>


    @GET("/api/EventDetailComponent/ED004")
    fun getED004(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>


    @GET("/api/EventDetailComponent/ED005")
    fun getED005(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>

    //EventPosterComponent
    @GET("/api/EventPosterComponent/EP001")
    fun getEP001(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>


    @GET("/api/EventPosterComponent/EP002")
    fun getEP002(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>


    @GET("/api/EventPosterComponent/EP003")
    fun getEP003(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>

    @GET("/api/EventPosterComponent/EP004")
    fun getEP004(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>

    @GET("/api/EventPosterComponent/EP005")
    fun getEP005(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>
    //EventSummaryComponent
    @GET("/api/EventSummaryComponent/ES001")
    fun getES001(
        @Query("EventId") eventId: Int?,
    ): Call<DictionaryResponseModel>

    //GridListComponent
    @GET("/api/GridListComponent/GL001")
    fun getGL001(
        @QueryMap() map: Map<String, String>? = null,
//--------------
        @Query("EventIds") eventIds: List<Int>? = null,
        @Query("EventGroupId") eventGroupId: Int? = null,
        @Query("BranchId") branchId: Int? = null,
        @Query("DayCount") dayCount: Int? = null,
        @Query("Type") type: Int? = null,
        @Query("ruleList") ruleList: List<RulesModel>? = null
    ): Call<ArrayDictionaryResponseModel>


    //ListComponent
    @GET("/api/ListComponent/L001")
    fun getL001(
        @QueryMap() map: Map<String, String>? = null,
//--------------
        @Query("FilmIds") filmIds: List<Int>?=null,
        @Query("EventGroupIds") eventGroupIds: List<Int>?=null,
        @Query("SliderIds") sliderIds: List<Int>?=null,
        @Query("EventGroupId") eventGroupId: Int?=null,

        @Query("BranchId") branchId: Int?=null,
        @Query("DayCount") dayCount: Int? = 14,
        @Query("Type") type: Int?=null,
        @Query("ruleList") ruleList: List<RulesModel>?=null
    ): Call<ArrayDictionaryResponseModel>


    @GET("/api/ListComponent/L002")
    fun getL002(
        @QueryMap() map: Map<String, String>? = null,

        @Query("OrganizerType") organizerType: Int?= null,
        @Query("CityId") cityId: Int?= null

    ): Call<ArrayDictionaryResponseModel>


    //MenuListComponent
    @GET("/api/MenuListComponent/ML001")
    fun getML001(): Call<ArrayDictionaryResponseModel>


    //RowListComponent
    @GET("/api/RowListComponent/RL001")
    fun getRL001(
        @QueryMap() map: Map<String, String>? = mapOf(("Type" to "8")),
    ): Call<ArrayDictionaryResponseModel>


    @GET("/api/ListComponent/RL002")
    fun getRL002(): Call<ArrayDictionaryResponseModel>


    @GET("/api/RowListComponent/RL003")
    fun getRL003(
        @QueryMap map: Map<String, String>? = mapOf(),
//--------------
        @Query("BranchIds") branchIds: List<Int>? = null,
        @Query("Type") type: Int? = null,
        @Query("OrganizerType") organizerType: Int? = null,
        @Query("ruleList") ruleList: List<RulesModel>? = null
    ): Call<ArrayDictionaryResponseModel>


    //SelectDateComponent
    @GET("/api/SelectDateComponent/SD001")
    fun getSD001(
        @Query("EventId") eventId: Int?,
        @Query("CinemaBranchId") cinemaBranchId: Int? = null,
    ): Call<ArrayDictionaryResponseModel>

    @GET("/api/SelectDateComponent/SD002")
    fun getSD002(
        @Query("BranchId") cinemaBranchId: Int? = null,
    ): Call<ArrayDictionaryResponseModel>


    //SliderComponent
    @GET("/api/SliderComponent/S001")
    fun getS001(
        // @QueryMap() map: Map<String, Int>? = mapOf(("Type" to 8)),
        @QueryMap() map: Map<String, String>? = null,
//--------------
        @Query("FilmIds") filmIds: List<Int>? = null,
        @Query("EventGroupIds") eventGroupIds: List<Int>? = null,
        @Query("SliderIds") sliderIds: List<Int>? = null,
        @Query("EventGroupId") eventGroupId: Int? = null,
        @Query("BranchId") branchId: Int? = null,
        @Query("DayCount") dayCount: Int? = null,
        @Query("Type") type: Int? = null,
        @Query("ruleList") ruleList: List<RulesModel>? = null
    ): Call<ArrayDictionaryResponseModel>


    @GET("/api/SliderComponent/S002")
    fun getS002(
        @QueryMap() map: Map<String, Int>? = mapOf(("Type" to 8)),
//--------------
        @Query("OrganizerType") organizerType: Int?,
        @Query("DayCount") dayCount: Int? = 14,
        @Query("Type") type: Int?,
        @Query("ruleList") ruleList: List<RulesModel>?
    ): Call<FragmentResponseModel>

    @GET("/api/SliderComponent/S003")
    fun getS003(
        @QueryMap() map: Map<String, String>? = null,
//--------------
        @Query("OrganizerType") organizerType: Int?=null,
        @Query("DayCount") dayCount: Int? = 21,
        @Query("Type") type: Int? = null,
        @Query("ruleList") ruleList: List<RulesModel>? = null
    ): Call<ArrayDictionaryResponseModel>

    //WideRowList
    @GET("/api/WideRowListComponent/WRL001")
    fun getWRL001(
        @QueryMap() map: Map<String, String>? = null,
//--------------
        @Query("EventGroupId") eventGroupId: Int? = null,
        @Query("BranchId") branchId: Int? = null,
        @Query("OrganizerType") organizerType: Int? = null,
        @Query("Type") type: Int? = null,
        @Query("ruleList") ruleList: List<RulesModel>? = null
    ): Call<ArrayDictionaryResponseModel>
    //Banner
    @GET("/api/Banner/B001")
    fun getB001(
        @QueryMap() map: Map<String, String>? = null,
//--------------
        @Query("EventGroupId") eventGroupId: Int? = null,
        @Query("BranchId") branchId: Int? = null,
        @Query("OrganizerType") organizerType: Int? = null,
        @Query("Type") type: Int? = null,
        @Query("ruleList") ruleList: List<RulesModel>? = null
    ): Call<DictionaryResponseModel>

    //Auth
    data class SignInBody(val email: String, val password: String)

    @POST("/api/Auth/SignIn")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun signIn(@Body signInBody: SignInBody): Call<DictionaryResponseModel>

    //Search

    @GET("/api/Search/SearchEvent")
    fun searchEvent(
        @Query("SearchKey") searchKey: String?,
    ): Call<ArrayDictionaryResponseModel>

    //Location

    @GET("/api/Location/GetCities")
    fun getCities(
        @Query("CountryId") countryId: Int?,
    ): Call<ArrayDictionaryResponseModel>

    @GET("/api/Location/GetCountries")
    fun getCountries(): Call<ArrayDictionaryResponseModel>


    //Seat
    @GET("/api/Seat/GetSaloonSeats")
    fun getSaloonSeats( @Query("SeanceId") seanceId: Int?=null,): Call<DictionaryResponseModel>


    @GET("/api/Seat/GetSeatListByBlock")
    fun getSeatListByBlock(
        @Query("SeanceId") seanceId: Int?=null,
        @Query("BlockId") blockId: Int?=null
    ): Call<ArrayDictionaryResponseModel>




    //Ticket
    @GET("/api/Ticket/GetUserTickets")
    fun getUserTickets(): Call<ArrayDictionaryResponseModel>


    @GET("/api/Ticket/GetTicketDetail")
    fun getTicketDetail(
        @Query("ReservationNumber") reservationNumber: String?=null,
    ): Call<DictionaryResponseModel>

    //User

    @GET("/api/User/GetUser")
    fun getUser(): Call<DictionaryResponseModel>

    @GET("/api/User/GetUserFavoriteBranches")
    fun getUserFavoriteBranches(): Call<ArrayDictionaryResponseModel>

    data class UpdateUserBody(
        val firstName: String,
        val lastName: String,
        val email: String,
    )

    @PUT("/api/User/UpdateUser")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun updateUser(@Body updateUserBody: UpdateUserBody): Call<DictionaryResponseModel>

    data class AddToFavoriteBranches(
        val branchId: Int
    )

    @POST("/api/User/AddToFavoriteBranches")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun addToFavoriteBranches(@Body addToFavoriteBranch: AddToFavoriteBranches): Call<ArrayDictionaryResponseModel>

    data class RemoveFromFavoriteBranches(
        val branchId: Int
    )

    @HTTP(method = "DELETE", path = "/api/User/RemoveFromFavoriteBranches", hasBody = true)
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun removeFromFavoriteBranches(@Body removeFromFavoriteBranches: RemoveFromFavoriteBranches): Call<ArrayDictionaryResponseModel>


    data class ChangePasswordBody(
        val id: Int=0,
        val oldPassword: String,
        val newPassword: String
    )

    @PUT("/api/User/ChangePassword")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    fun updatePassword(@Body changePasswordBody: ChangePasswordBody): Call<DictionaryResponseModel>
//------------END OF SWAGGER----------------


    @GET("/home")
    fun getHomeData(): Call<FragmentResponseModel?>


    @GET("/discover")
    fun getDiscoverData(): Call<FragmentResponseModel>

    @GET("/search/{search}")
    fun search(
        @Path(value = "search", encoded = true) searchText: String?
    ): Call<ArrayList<ComponentModel>>

    @GET("/tickets")
    fun getTicketsData(): Call<ArrayList<ComponentModel>>

    @GET("/event/{filmId}")
    fun getEventDetail(
        @Path(value = "filmId", encoded = true) eventId: Int?
    ): Call<FragmentResponseModel>


    @GET("/category/{filmId}")
    fun getCategory(
        @Path(value = "filmId", encoded = true) eventId: Int?
    ): Call<FragmentResponseModel?>


    @GET("/eventgroup/{eventGroupId}")
    fun getEventGroupDetail(
        @Path(value = "eventGroupId", encoded = true) eventGroupId: Int?
    ): Call<JsonArray>

    @GET("/venuepicker/11/25485?date=2022-11-19")
    fun getVenuePickerData(): Call<FragmentResponseModel?>

    @GET("/getVenueList/11/25485?date=2022-11-19")
    fun getVenueListData(): Call<VenueResponseModel?>

    /*
    /////BİLETİNİAL SERVİCES
    @FormUrlEncoded
    @POST("/token")
    fun userLogin(@FieldMap options: Map<String?, String?>?): Call<JsonObject?>?



    @FormUrlEncoded
    @POST("/api/account/ForgotPassword")
    fun forgotPassword(
        @Field("email") email: String?,
        @Field("SiteId") SiteId: Int
    ): Call<JsonObject?>?

    @get:GET("/api/event/CityAll")
    val cityAllList: Call<JsonArray?>?

    @get:GET("/api/event/City")
    val cityList: Call<JsonArray?>?

    @GET("/api/movie/nextprogram")
    fun getNextProgramList(
        @Query("cityId") cityId: Int
    ): Call<JsonArray?>?


    @FormUrlEncoded
    @POST("/api/account/UpdateMyProfile")
    fun updateMyProfile(
        @FieldMap body: Map<String?, String?>?,
        @Field("aboneOl") aboneOl: Boolean
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/account/ChangePassword")
    fun changePassword(
        @FieldMap body: Map<String?, String?>?,
        @Field("SiteId") SiteId: Int
    ): Call<JsonObject?>?

    @GET("/api/account/MyTickets")
    fun getMyTickets(
        @QueryMap data: Map<String?, String?>?
    ): Call<JsonObject?>?

    @GET("/api/Account/MyProfile")
    fun userProfileGet(): Call<JsonObject?>?

    @GET("/api/movie/SearchOrganizationList")
    fun searchOrganizations(
        @Query("cityId") cityId: Int,
        @Query("search") search: String?,
        @Query("searchPlace") searchPlace: Boolean
    ): Call<JsonObject?>?

    @get:GET("/api/event/AllFaq")
    val faqDetailList: Call<JsonArray?>?

    @GET("/api/cinema/SeanceByCityGroupSummary/{cityId}")
    fun getCinemaSeanceByCityGroupSummary(
        @Path(value = "cityId", encoded = true) cityId: Int,
        @QueryMap data: Map<String?, String?>?
    ): Call<JsonObject?>?

    @GET("/api/cinema/SeanceByCity/{cityId}")
    fun getSeanceByCity(
        @Path(value = "cityId", encoded = true) cityId: Int,
        @QueryMap data: Map<String?, String?>?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/process/AddToBasketNoSeat")
    fun addToBasketNoSeat(
        @FieldMap body: Map<String?, Int?>?,
        @FieldMap body2: Map<String?, String?>?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/process/RemoveBasketNoSeat")
    fun deleteFromBasketNoSeat(
        @FieldMap body: Map<String?, String?>?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/process/addtobasket")
    fun addToBasket(
        @FieldMap body: Map<String?, Int?>?,
        @FieldMap body2: Map<String?, String?>?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/process/deletefrombasket")
    fun deleteFromBasket(
        @FieldMap body: Map<String?, Int?>?,
        @FieldMap body2: Map<String?, String?>?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/process/deletefrombasket")
    fun deleteFromBasket(
        @FieldMap body2: Map<String?, String?>?
    ): Call<JsonObject?>?

    @GET("/api/process/GetDynamicBlocks")
    fun getDynamicsBlock(
        @Query("seanceId") seanceId: Int
    ): Call<JsonObject?>?

    @GET("/api/process/GetDynamicSeatsWithBlock")
    fun getDynamicSeatsWithBlock(
        @Query("seanceId") seanceId: Int,
        @Query("blockId") blockId: Int
    ): Call<JsonObject?>?

    @GET("/api/event/GetAllSettings")
    fun allSettings(
        @Query("cinemaBranchId") seanceId: Int
    ): Call<JsonObject?>?

    @GET("/api/process/GetSeatsNew")
    fun getSeatList(
        @Query("seanceId") seanceId: Int,
        @Query("getTicketType") getTicketType: Boolean
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/process/get_payment_summary")
    fun getPaymentSummary(
        @FieldMap body: Map<String?, Int?>?,
        @FieldMap body2: Map<String?, String?>?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/wallet/Info")
    fun getWalletInfo(
        @FieldMap body: Map<String?, Int?>?,
        @FieldMap body2: Map<String?, String?>?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/process/get_seance_ticketypes")
    fun getSeanceTicketType(
        @FieldMap body: Map<String?, Int?>?
    ): Call<JsonObject?>?

    @GET("/api/cinema/OrganizationSaloonDetail/{saloonId}")
    fun organizationSaloonDetail(
        @Path(value = "saloonId", encoded = true) saloonId: Int
    ): Call<JsonObject?>?

    @GET("/api/cinema/OrganizationBranchDetail/{branchId}")
    fun organizationBranchDetail(
        @Path(value = "branchId", encoded = true) branchId: Int
    ): Call<JsonObject?>?

    @GET("/api/cinema/seancebybranch/{branchId}")
    fun seanceByBranch(
        @Path(value = "branchId", encoded = true) branchId: Int,
        @Query("suresiGecenleriGosterme") suresiGecenleriGosterme: Boolean?,
        @Query("date") date: String?
    ): Call<JsonArray?>?

    @GET("/api/cinema/seancebysaloon/{saloonId}")
    fun seanceBySaloon(
        @Path(value = "saloonId", encoded = true) branchId: Int,
        @Query("suresiGecenleriGosterme") suresiGecenleriGosterme: Boolean?,
        @Query("date") date: String?
    ): Call<JsonArray?>?

    @get:GET("/api/event/GetReleaseMobileAppVersion/2")
    val releaseVersion: Call<JsonObject?>?

    @GET("/api/cinema/location")
    fun getClosestSaloon(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("filmId") filmId: Int
    ): Call<JsonArray?>?

    @GET("/api/cinema/location")
    fun getClosestSaloon(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Call<JsonArray?>?

    @GET("/api/cinema/AllPlace")
    fun getAllPlace(
        @Query("myFavorites") myFavorites: Boolean,
        @Query("cityId") cityId: Int,
        @Query("organizerType") organizerType: Int
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/cinema/AddFavoriteBranch")
    fun addFavoriteBranch(
        @Field("CinemaBranchId") cinemaBranchId: Int
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/cinema/RemoveFavoriteBranch")
    fun removeFavoriteBranch(
        @Field("CinemaBranchId") cinemaBranchId: Int
    ): Call<JsonObject?>?

    @GET("/api/cinema/SeanceByBranchGroupSummary/{branchId}")
    fun getSeanceByBranchGroupSummary(
        @Path(value = "branchId", encoded = true) branchId: Int,
        @QueryMap data: Map<String?, String?>?
    ): Call<JsonObject?>?

    @GET("/api/cinema/SeanceByBranchGroup/{branchId}")
    fun getSeanceByBranchGroup(
        @Path(value = "branchId", encoded = true) branchId: Int,
        @QueryMap data: Map<String?, String?>?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/process/GetBkmExpressToken")
    fun getBkmExpressToken(
        @FieldMap body: Map<String?, String?>?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/process/FastPaySuccess")
    fun getFastPaySuccess(
        @FieldMap body: Map<String?, String?>?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/process/BkmEkspresPaySuccess")
    fun getBkmEkspresPaySuccess(
        @FieldMap body: Map<String?, String?>?
    ): Call<JsonObject?>?

    @GET("/api/event/GetSliderGroupsForPage")
    fun getSliderGroupsForPage(
        @Query("cityId") cityId: Int,
        @Query("organizerType") organizerType: Int
    ): Call<JsonObject?>?

    @GET("/api/cinema/GetEventCalendarSummary/{cinemaId}")
    fun getEventCalendarSummary(
        @Path(value = "cinemaId", encoded = true) cinemaId: Int
    ): Call<JsonObject?>?

    @GET("/api/cinema/GetDatesForEventCalendar/{cinemaId}")
    fun getDatesForEventCalendar(
        @Path(value = "cinemaId", encoded = true) cinemaId: Int,
        @Query("cityId") cityId: Int
    ): Call<JsonObject?>?

    @GET("/api/cinema/GetEventsForEventCalendar/{cinemaId}")
    fun getEventsForEventCalendar(
        @Path(value = "cinemaId", encoded = true) cinemaId: Int,
        @Query("cityId") cityId: Int,
        @Query("startdate") startdate: String?,
        @Query("finishdate") finishdate: String?
    ): Call<JsonObject?>?

    @GET("/api/movie/GetSliderOrganizationList")
    fun getSliderOrganizationList( //@Query("cityId") int cityId,
        @Query("eventGroupId") eventGroupId: String?,
        @Query("otherName") otherName: String?
    ): Call<JsonObject?>?

    @GET("/api/event/GetLocationFromCity")
    fun getLocationFromCity(
        @QueryMap body: Map<String?, GET?>?
    ): Call<JsonArray?>?

    @GET("/api/event/GetActiveFilmTypeList")
    fun getActiveFilmTypeList(
        @QueryMap body: Map<String?, GET?>?
    ): Call<JsonArray?>?

    @GET("/api/movie/GetOrganizationListDateSummary")
    fun getOrganizationListDateSummary(
        @QueryMap body: Map<String?, GET?>?
    ): Call<JsonArray?>?

    @GET("/api/account/GetTicketDetails")
    fun getTicketDetails(
        @Query("rezervationNumber") rezervationNumber: String?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/account/CancelMyTicket")
    fun cancelMyTicket(
        @Field("RezervationNumber") rezervationNumber: String?,
        @Field("TicketIdList") ticketIdList: List<Int?>?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/process/AddCoupon")
    fun addCoupon(
        @Field("SessionId") sessionId: String?,
        @Field("TicketTypeId") ticketIdList: List<Int?>?,
        @Field("CinemaBranchId") cinemaBranchId: Int,
        @Field("SaloonId") saloonId: Int,
        @Field("SeanceId") seanceId: Int,
        @Field("NoSeat") noSeat: Int,
        @Field("CouponNo") couponNo: String?,
        @Field("Coupons") coupons: List<String?>?,
        @Field("ProductId") productId: String?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/account/UpdateFcmToken")
    fun updateFCMToken(
        @Field("FcmToken") FcmToken: String?,
        @Field("SiteId") SiteId: Int
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/account/Activate")
    fun activateRegistiration(
        @Field("Token") Token: String?
    ): Call<JsonObject?>?

    @get:GET("/api/event/GetPageContentList")
    val pageContentList: Call<JsonArray?>?

    @GET("/api/event/GetPageContent/{url}")
    fun getPageContent(
        @Path(value = "url", encoded = true) url: String?
    ): Call<JsonObject?>?

    @GET("/api/event/GetPeopleInfo/{castId}")
    fun getPeopleInfo(
        @Path(value = "castId", encoded = true) castId: Int
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/account/ResetPasswordConfirm")
    fun resetPasswordConfirm(
        @FieldMap data: Map<String?, String?>
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("api/account/ReSendActivateMail")
    fun reSendActivateMail(
        @Field("email") email: String?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("api/account/SendPhoneVerificationSmsRequest")
    fun sendPhoneVerificationSmsRequest(
        @Field("phoneNumber") phoneNumber: String?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("api/account/SendPhoneVerificationRequest")
    fun sendPhoneVerificationRequest(
        @Field("smsCode") smsCode: String?
    ): Call<JsonObject?>?

    @get:GET("/api/event/GetOrganizerTypeList")
    val organizerTypeList: Call<JsonObject?>?

    @GET("/api/account/GetFilmComments/{filmId}")
    fun getFilmComments(
        @Path(value = "filmId", encoded = true) filmId: Int
    ): Call<JsonObject?>?

    @GET("/api/event/GetGroupsForPage")
    fun getGroupsForPage(
        @Query("link") link: String?
    ): Call<JsonObject?>?

    @FormUrlEncoded
    @POST("/api/account/AddComment")
    fun addComment(
        @Field("FilmId") filmId: Int,
        @Field("Rate") rate: Float,
        @Field("Detail") detail: String?
    ): Call<JsonObject?>?

    // TODO: 18.08.2020
    //<------------------------------------------------------->
    @get:GET("/api/event/CityAllSanatCepte")
    val cityFilteredList: Call<JsonArray?>?

    @GET("/api/movie/GetSanatCepteOrganizationList")
    fun getOrganizationList(
        @QueryMap body: Map<String?, Int?>?,
        @Query(value = "suresiGecenleriGosterme") suresiGecenleriGosterme: Boolean //@Query(value = "search") String search
    ): Call<JsonObject?>?

    @GET("/api/movie/GetSanatCepteOrganizationList")
    fun getOrganizationList(
        @QueryMap body: Map<String?, Int?>?,
        @Query(value = "suresiGecenleriGosterme") suresiGecenleriGosterme: Boolean,
        @Query(value = "search") search: String?
    ): Call<JsonObject?>?

    @get:GET("/api/account/SanatCepteMyTickets")
    val myTickets: Call<JsonObject?>?

    */
}
