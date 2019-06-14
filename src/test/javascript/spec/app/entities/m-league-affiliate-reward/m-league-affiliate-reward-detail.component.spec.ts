/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLeagueAffiliateRewardDetailComponent } from 'app/entities/m-league-affiliate-reward/m-league-affiliate-reward-detail.component';
import { MLeagueAffiliateReward } from 'app/shared/model/m-league-affiliate-reward.model';

describe('Component Tests', () => {
  describe('MLeagueAffiliateReward Management Detail Component', () => {
    let comp: MLeagueAffiliateRewardDetailComponent;
    let fixture: ComponentFixture<MLeagueAffiliateRewardDetailComponent>;
    const route = ({ data: of({ mLeagueAffiliateReward: new MLeagueAffiliateReward(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLeagueAffiliateRewardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MLeagueAffiliateRewardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MLeagueAffiliateRewardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mLeagueAffiliateReward).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
