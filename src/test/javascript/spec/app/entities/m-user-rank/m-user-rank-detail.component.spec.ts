/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MUserRankDetailComponent } from 'app/entities/m-user-rank/m-user-rank-detail.component';
import { MUserRank } from 'app/shared/model/m-user-rank.model';

describe('Component Tests', () => {
  describe('MUserRank Management Detail Component', () => {
    let comp: MUserRankDetailComponent;
    let fixture: ComponentFixture<MUserRankDetailComponent>;
    const route = ({ data: of({ mUserRank: new MUserRank(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MUserRankDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MUserRankDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MUserRankDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mUserRank).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
