import 'package:flutter/material.dart';
import 'package:modernlogintute/service/offer_service.dart';

class Home extends StatelessWidget {
  const Home({super.key});

  @override
  Widget build(BuildContext context) {
    return const Scaffold(
      body: CardCarousel(),
    );
  }
}

class CardCarousel extends StatefulWidget {
  const CardCarousel({super.key});

  @override
  _CardCarouselState createState() => _CardCarouselState();
}

class _CardCarouselState extends State<CardCarousel> {
  // final List<String> offers = [
  //   'Special Offer 1',
  //   'Special Offer 2',
  //   'Special Offer 3'
  // ];
  int currentIndex = 0;

  void _handleAccept() {
    // print('Accepted: ${offers[currentIndex]}');
    _moveToNextCard();
  }

  void _handleRefuse() {
    // print('Refused: ${offers[currentIndex]}');
    _moveToNextCard();
  }

  void _moveToNextCard() {
    // setState(() {
    //   currentIndex = (currentIndex + 1).clamp(0, offers.length - 1);
    // });
  }

  void _moveToPreviousCard() {
    // setState(() {
    //   currentIndex = (currentIndex - 1).clamp(0, offers.length - 1);
    // });
  }

  @override
  Widget build(BuildContext context) {
    return FutureBuilder(
        future: OfferService.getData(),
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            return
                // snapshot.data.length == 0
                //     ? const NoDataWidget()
                //     :
                Padding(
                    padding: const EdgeInsets.only(top: 0.0, left: 8, right: 8),
                    child: _build(snapshot.data));
          } else if (snapshot.hasError) {
            // return const IErrorWidget();
            return Container();
          } else {
            // return const LoadingIndicatorWidget();
            return Container();
          }
        });
  }

  Widget _build(offers) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        SizedBox(
          height: 500.0, // Adjust the height as needed
          child: Card(
            margin: const EdgeInsets.all(16.0),
            color: Colors.lightBlue[100], // Set light blue background color
            child: Column(
              children: [
                ListTile(
                  title: Text(offers[currentIndex]),
                ),
                ButtonBar(
                  alignment: MainAxisAlignment.center,
                  children: [
                    ElevatedButton(
                      onPressed: _handleAccept,
                      child: const Text('Accept'),
                    ),
                    ElevatedButton(
                      onPressed: _handleRefuse,
                      child: const Text('Refuse'),
                    ),
                  ],
                ),
              ],
            ),
          ),
        ),
        const SizedBox(height: 16.0),
        Row(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: _moveToPreviousCard,
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.orange,
              ),
              child: const Text('Previous'),
            ),
            const SizedBox(width: 16.0),
            ElevatedButton(
              onPressed: _moveToNextCard,
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.orange,
              ),
              child: const Text('Next'),
            ),
          ],
        ),
      ],
    );
  }
}
