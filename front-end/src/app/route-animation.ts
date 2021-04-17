import {
    trigger,
    transition,
    style,
    query,
    group,
    animateChild,
    animate,
    keyframes,
  } from '@angular/animations';

export const slider =
  trigger('routeAnimations', [
    transition('isRight => isLeft', slideTo('left') ),
    transition('isLeft => isRight', slideTo('right') ),
    transition('isRight => righter', slideTo('right') ), 
    transition('righter => isRight', slideTo('left') ),
    transition('righter => evenRighter', slideTo('right') ), 
    transition('evenRighter => righter', slideTo('right') ), 
    transition('evenRighter => isRight', slideTo('left') ),
    transition('isRight => evenRighter', slideTo('right') ), 
    transition('righter => isLeft', slideTo('left') )
  ]);

function slideTo(direction) {
  const optional = { optional: true };
  return [
    query(':enter, :leave', [
      style({
        position: 'absolute',
        top: 0,
        [direction]: 0,
        width: '100%'
      })
    ], optional),
    query(':enter', [
      style({ [direction]: '-100%'})
    ]),
    group([
      query(':leave', [
        animate('600ms ease', style({ [direction]: '100%'}))
      ], optional),
      query(':enter', [
        animate('600ms ease', style({ [direction]: '0%'}))
      ])
    ]),
    // Normalize the page style... Might not be necessary

    // Required only if you have child animations on the page
    // query(':leave', animateChild()),
    // query(':enter', animateChild()),
  ];
}


