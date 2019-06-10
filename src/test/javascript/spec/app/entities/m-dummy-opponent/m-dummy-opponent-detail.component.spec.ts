/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDummyOpponentDetailComponent } from 'app/entities/m-dummy-opponent/m-dummy-opponent-detail.component';
import { MDummyOpponent } from 'app/shared/model/m-dummy-opponent.model';

describe('Component Tests', () => {
  describe('MDummyOpponent Management Detail Component', () => {
    let comp: MDummyOpponentDetailComponent;
    let fixture: ComponentFixture<MDummyOpponentDetailComponent>;
    const route = ({ data: of({ mDummyOpponent: new MDummyOpponent(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDummyOpponentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDummyOpponentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDummyOpponentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDummyOpponent).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
