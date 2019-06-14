/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpRankingRewardUpdateComponent } from 'app/entities/m-pvp-ranking-reward/m-pvp-ranking-reward-update.component';
import { MPvpRankingRewardService } from 'app/entities/m-pvp-ranking-reward/m-pvp-ranking-reward.service';
import { MPvpRankingReward } from 'app/shared/model/m-pvp-ranking-reward.model';

describe('Component Tests', () => {
  describe('MPvpRankingReward Management Update Component', () => {
    let comp: MPvpRankingRewardUpdateComponent;
    let fixture: ComponentFixture<MPvpRankingRewardUpdateComponent>;
    let service: MPvpRankingRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpRankingRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MPvpRankingRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MPvpRankingRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPvpRankingRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MPvpRankingReward(123);
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
        const entity = new MPvpRankingReward();
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
