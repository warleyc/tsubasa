/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpRankingRewardDetailComponent } from 'app/entities/m-pvp-ranking-reward/m-pvp-ranking-reward-detail.component';
import { MPvpRankingReward } from 'app/shared/model/m-pvp-ranking-reward.model';

describe('Component Tests', () => {
  describe('MPvpRankingReward Management Detail Component', () => {
    let comp: MPvpRankingRewardDetailComponent;
    let fixture: ComponentFixture<MPvpRankingRewardDetailComponent>;
    const route = ({ data: of({ mPvpRankingReward: new MPvpRankingReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpRankingRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MPvpRankingRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPvpRankingRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mPvpRankingReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
