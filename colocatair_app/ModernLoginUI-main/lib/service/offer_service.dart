import 'dart:convert';
import 'dart:developer';
import 'package:modernlogintute/model/offer_model.dart';
import 'package:modernlogintute/config.dart';
import 'package:http/http.dart' as http;

class OfferService {
  static const _viewUrl = "$apiUrlOffer/offer/all";

  static List<OfferModel> dataFromJson(String jsonString) {
    final data = json.decode(jsonString);
    return List<OfferModel>.from(data.map((item) => OfferModel.fromJson(item)));
  }

  static Future<List<OfferModel>> getData() async {
    final response = await http.get(Uri.parse(_viewUrl));
    log(response.body.toString());
    if (response.statusCode == 200) {
      List<OfferModel> list = dataFromJson(response.body);
      log("message : ${response.body}");
      return list;
    } else {
      return []; //if any error occurs then it return a blank list
    }
  }
}
