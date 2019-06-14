/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLeagueAffiliateRewardUpdateComponent } from 'app/entities/m-league-affiliate-reward/m-league-affiliate-reward-update.component';
import { MLeagueAffiliateRewardService } from 'app/entities/m-league-affiliate-reward/m-league-affiliate-reward.service';
import { MLeagueAffiliateReward } from 'app/shared/model/m-league-affiliate-reward.model';

describe('Component Tests', () => {
  describe('MLeagueAffiliateReward Management Update Component', () => {
    let comp: MLeagueAffiliateRewardUpdateComponent;
    let fixture: ComponentFixture<MLeagueAffiliateRewardUpdateComponent>;
    let service: MLeagueAffiliateRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLeagueAffiliateRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MLeagueAffiliateRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MLeagueAffiliateRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLeagueAffiliateRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MLeagueAffiliateReward(123);
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
        const entity = new MLeagueAffiliateReward();
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
