import 'dart:convert';
import 'dart:developer';
import 'package:modernlogintute/config.dart';
import 'package:modernlogintute/model/user_model.dart';
import 'package:http/http.dart' as http;

class AuthService {
  static const _signupUrl = "$apiUrlUser/save";

  static signup(UserModel userModel) async {
    log("message1é3== $userModel");
    final res = await http.post(
      Uri.parse(_signupUrl),
      headers: {'Content-Type': 'application/json'},
      body: json.encode(userModel.toJson()),
    );
    log("message1");

    final data = json.decode(res.body);
    log("message123 $data");

    if (res.statusCode == 200) {
      log("message");

      return data;
    } else {
      log("messages");

      return "error";
    }
  }
}
