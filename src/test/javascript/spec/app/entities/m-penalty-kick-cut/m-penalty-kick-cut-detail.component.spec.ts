/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPenaltyKickCutDetailComponent } from 'app/entities/m-penalty-kick-cut/m-penalty-kick-cut-detail.component';
import { MPenaltyKickCut } from 'app/shared/model/m-penalty-kick-cut.model';

describe('Component Tests', () => {
  describe('MPenaltyKickCut Management Detail Component', () => {
    let comp: MPenaltyKickCutDetailComponent;
    let fixture: ComponentFixture<MPenaltyKickCutDetailComponent>;
    const route = ({ data: of({ mPenaltyKickCut: new MPenaltyKickCut(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPenaltyKickCutDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MPenaltyKickCutDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPenaltyKickCutDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mPenaltyKickCut).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
