import 'dart:convert';
import 'dart:developer';
import 'package:modernlogintute/config.dart';
import 'package:modernlogintute/model/user_model.dart';
import 'package:http/http.dart' as http;

class AuthService {
  static const _signupUrl = "$apiUrlUser/save";

  static signup(UserModel userModel) async {
    log("=========================  ${userModel.toJson()}");

    final res = await http.post(
      Uri.parse(_signupUrl),
      headers: {'Content-Type': 'application/json'},
      body: json.encode(userModel.toJson()),
    );
    final data = json.decode(res.body);
    if (res.statusCode == 200) {
      return data;
    } else {
      return "error";
    }
  }
}
