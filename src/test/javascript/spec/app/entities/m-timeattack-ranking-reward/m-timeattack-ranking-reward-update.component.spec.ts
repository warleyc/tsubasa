/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTimeattackRankingRewardUpdateComponent } from 'app/entities/m-timeattack-ranking-reward/m-timeattack-ranking-reward-update.component';
import { MTimeattackRankingRewardService } from 'app/entities/m-timeattack-ranking-reward/m-timeattack-ranking-reward.service';
import { MTimeattackRankingReward } from 'app/shared/model/m-timeattack-ranking-reward.model';

describe('Component Tests', () => {
  describe('MTimeattackRankingReward Management Update Component', () => {
    let comp: MTimeattackRankingRewardUpdateComponent;
    let fixture: ComponentFixture<MTimeattackRankingRewardUpdateComponent>;
    let service: MTimeattackRankingRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTimeattackRankingRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTimeattackRankingRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTimeattackRankingRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTimeattackRankingRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTimeattackRankingReward(123);
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
        const entity = new MTimeattackRankingReward();
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
