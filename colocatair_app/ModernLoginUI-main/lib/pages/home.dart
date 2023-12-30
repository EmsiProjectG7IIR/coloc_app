import 'package:flutter/material.dart';
import 'package:modernlogintute/model/offer_model.dart';
import 'package:modernlogintute/service/offer_service.dart'; // Import your OfferModel

class Home extends StatelessWidget {
  const Home({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: CardCarousel(),
    );
  }
}

class CardCarousel extends StatefulWidget {
  const CardCarousel({Key? key}) : super(key: key);

  @override
  _CardCarouselState createState() => _CardCarouselState();
}

class _CardCarouselState extends State<CardCarousel> {
  late List<OfferModel> offers;
  int currentIndex = 0;

  @override
  void initState() {
    super.initState();
    fetchOffers();
  }

  Future<void> fetchOffers() async {
    final data = await OfferService.getData();
    setState(() {
      offers = data.cast<OfferModel>();
    });
  }

  void _handleAccept() {
    // Handle accept button click
    // You can make another API call or perform other actions here
    _moveToNextCard();
  }

  void _handleRefuse() {
    // Handle refuse button click
    // You can make another API call or perform other actions here
    _moveToNextCard();
  }

  void _moveToNextCard() {
    setState(() {
      currentIndex = (currentIndex + 1).clamp(0, offers.length - 1);
    });
  }

  void _moveToPreviousCard() {
    setState(() {
      currentIndex = (currentIndex - 1).clamp(0, offers.length - 1);
    });
  }

  @override
  Widget build(BuildContext context) {
    if (offers.isEmpty) {
      // Handle case where offers are empty
      return const Center(
        child: Text('No offers available.'),
      );
    }

    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        SizedBox(
          height: 500.0,
          child: Card(
            margin: const EdgeInsets.all(16.0),
            color: Colors.lightBlue[100],
            child: Column(
              children: [
                ListTile(
                  title: Text(offers[currentIndex].titre ?? ''),
                  subtitle: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text('Description: ${offers[currentIndex].description ?? ''}'),
                      Text('Start Date: ${offers[currentIndex].dateDebut ?? ''}'),
                      Text('End Date: ${offers[currentIndex].dateFin ?? ''}'),
                      Text('Amount: ${offers[currentIndex].montant ?? ''}'),
                    ],
                  ),
                ),
                ButtonBar(
                  alignment: MainAxisAlignment.center,
                  children: [
                    ElevatedButton(
                      onPressed: _handleAccept,
                      child: Text('Accept'),
                    ),
                    ElevatedButton(
                      onPressed: _handleRefuse,
                      child: Text('Refuse'),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
        SizedBox(height: 16.0),
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: _moveToPreviousCard,
              child: Text('Previous'),
              style: ElevatedButton.styleFrom(
                primary: Colors.orange, 
              ),
            ),
            SizedBox(width: 16.0),
            ElevatedButton(
              onPressed: _moveToNextCard,
              child: Text('Next'),
              style: ElevatedButton.styleFrom(
                primary: Colors.orange, 
              ),
            ),
          ],
        ),
      ],
    );
  }
}
