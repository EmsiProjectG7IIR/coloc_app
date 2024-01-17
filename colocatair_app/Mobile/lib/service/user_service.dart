import 'dart:convert';
import 'dart:developer';
import 'package:modernlogintute/config.dart';
import 'package:http/http.dart' as http;
import 'package:modernlogintute/model/user_model.dart';

class UserService {
  static const _viewUrl = "$apiUrlUser/all";
  static const _addUrl = "$apiUrlUser/save";
  static const _findUrl = "$apiUrlUser/findByUid";

  static List<UserModel> dataFromJson(String jsonString) {
    final data = json.decode(jsonString);
    // log(data.toString());
    return List<UserModel>.from(data.map((item) => UserModel.fromJson(item)));
  }

  static UserModel data1FromJson(String jsonString) {
    final data = json.decode(jsonString);
    return UserModel.fromJson(data);
  }

  static Future<List<UserModel>> getData(userId) async {
    final response = await http.get(Uri.parse("$_viewUrl/$userId"));
    if (response.statusCode == 200) {
      List<UserModel> list = dataFromJson(response.body);
      // log ("message : "+list[0].firstName);
      return list;
    } else {
      return []; //if any error occurs then it return a blank list
    }
  }

  static Future<UserModel?> getDataByUid(userId) async {
    final response = await http.get(Uri.parse("$_findUrl/$userId"));
    if (response.statusCode == 200) {
      UserModel user = data1FromJson(response.body);
      return user;
    } else {
      // Return null or a default UserModel in case of an error
      return null;
    }
  }

  static addData(UserModel userModel) async {
    log("message + ${userModel.uid}");
    final res = await http.post(Uri.parse(_addUrl), body: userModel.toJson());
    if (res.statusCode == 200) {
      log("message");
      return res.body;
    } else {
      return "error";
    }
  }

  // static updateFcmId(String uId, String fcmId) async {
  //   final res =
  //       await http.put(Uri.parse(_update), body: {"fcmId": fcmId, "uId": uId});
  //   if (res.statusCode == 200) {
  //     return res.body;
  //   } else {
  //     return "error";
  //   }
  // }

  // static updateData(UserModel userModel) async {
  //   final res =
  //       await http.put(Uri.parse(_updateUrl), body: userModel.toUpdateJson());
  //   // log(">>>>>>>>>>>>>>>>>>>>>>${userModel.toUpdateJson()}");
  //   if (res.statusCode == 200) {
  //     return res.body;
  //   } else {
  //     return "error";
  //   }
  // }

  // static Future<List<UserModel>> fetchNotificationStatusPatient() async {
  //   SharedPreferences pref = await SharedPreferences.getInstance();
  //   String uId = pref.getString("uId");
  //   final res = await http.get(Uri.parse("$_notifUrl/$uId"));
  //   if (res.statusCode == 200) {
  //     List<UserModel> list = dataFromJson(res.body);
  //     // log ("message " +res.body.toString());
  //     return list;
  //   } else {
  //     return [];
  //   }
  // }

  // static updateIsAnyNotification(isNotif) async {
  //   SharedPreferences pref = await SharedPreferences.getInstance();
  //   String uId = pref.getString("uId");
  //   log(isNotif + " : " + uId);
  //   final res = await http.put(Uri.parse(_updateNotifUrl),
  //       body: {"isAnyNotification": isNotif, "uId": uId});
  //   if (res.statusCode == 200) {
  //     return res.body;
  //   } else {
  //     return "error";
  //   }
  // }
}
