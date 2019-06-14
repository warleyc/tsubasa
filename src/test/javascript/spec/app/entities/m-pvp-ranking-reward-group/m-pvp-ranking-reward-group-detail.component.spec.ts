/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpRankingRewardGroupDetailComponent } from 'app/entities/m-pvp-ranking-reward-group/m-pvp-ranking-reward-group-detail.component';
import { MPvpRankingRewardGroup } from 'app/shared/model/m-pvp-ranking-reward-group.model';

describe('Component Tests', () => {
  describe('MPvpRankingRewardGroup Management Detail Component', () => {
    let comp: MPvpRankingRewardGroupDetailComponent;
    let fixture: ComponentFixture<MPvpRankingRewardGroupDetailComponent>;
    const route = ({ data: of({ mPvpRankingRewardGroup: new MPvpRankingRewardGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpRankingRewardGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MPvpRankingRewardGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPvpRankingRewardGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mPvpRankingRewardGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
