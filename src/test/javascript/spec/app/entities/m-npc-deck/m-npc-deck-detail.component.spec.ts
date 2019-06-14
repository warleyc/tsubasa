/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MNpcDeckDetailComponent } from 'app/entities/m-npc-deck/m-npc-deck-detail.component';
import { MNpcDeck } from 'app/shared/model/m-npc-deck.model';

describe('Component Tests', () => {
  describe('MNpcDeck Management Detail Component', () => {
    let comp: MNpcDeckDetailComponent;
    let fixture: ComponentFixture<MNpcDeckDetailComponent>;
    const route = ({ data: of({ mNpcDeck: new MNpcDeck(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MNpcDeckDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MNpcDeckDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MNpcDeckDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mNpcDeck).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
