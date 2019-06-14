/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MInitUserDeckDetailComponent } from 'app/entities/m-init-user-deck/m-init-user-deck-detail.component';
import { MInitUserDeck } from 'app/shared/model/m-init-user-deck.model';

describe('Component Tests', () => {
  describe('MInitUserDeck Management Detail Component', () => {
    let comp: MInitUserDeckDetailComponent;
    let fixture: ComponentFixture<MInitUserDeckDetailComponent>;
    const route = ({ data: of({ mInitUserDeck: new MInitUserDeck(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MInitUserDeckDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MInitUserDeckDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MInitUserDeckDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mInitUserDeck).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
