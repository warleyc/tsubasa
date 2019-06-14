/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MRegulatedLeagueRankingRewardUpdateComponent } from 'app/entities/m-regulated-league-ranking-reward/m-regulated-league-ranking-reward-update.component';
import { MRegulatedLeagueRankingRewardService } from 'app/entities/m-regulated-league-ranking-reward/m-regulated-league-ranking-reward.service';
import { MRegulatedLeagueRankingReward } from 'app/shared/model/m-regulated-league-ranking-reward.model';

describe('Component Tests', () => {
  describe('MRegulatedLeagueRankingReward Management Update Component', () => {
    let comp: MRegulatedLeagueRankingRewardUpdateComponent;
    let fixture: ComponentFixture<MRegulatedLeagueRankingRewardUpdateComponent>;
    let service: MRegulatedLeagueRankingRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MRegulatedLeagueRankingRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MRegulatedLeagueRankingRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MRegulatedLeagueRankingRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MRegulatedLeagueRankingRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MRegulatedLeagueRankingReward(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MRegulatedLeagueRankingReward();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
