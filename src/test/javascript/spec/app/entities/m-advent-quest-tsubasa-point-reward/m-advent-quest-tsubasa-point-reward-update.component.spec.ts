/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAdventQuestTsubasaPointRewardUpdateComponent } from 'app/entities/m-advent-quest-tsubasa-point-reward/m-advent-quest-tsubasa-point-reward-update.component';
import { MAdventQuestTsubasaPointRewardService } from 'app/entities/m-advent-quest-tsubasa-point-reward/m-advent-quest-tsubasa-point-reward.service';
import { MAdventQuestTsubasaPointReward } from 'app/shared/model/m-advent-quest-tsubasa-point-reward.model';

describe('Component Tests', () => {
  describe('MAdventQuestTsubasaPointReward Management Update Component', () => {
    let comp: MAdventQuestTsubasaPointRewardUpdateComponent;
    let fixture: ComponentFixture<MAdventQuestTsubasaPointRewardUpdateComponent>;
    let service: MAdventQuestTsubasaPointRewardService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAdventQuestTsubasaPointRewardUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MAdventQuestTsubasaPointRewardUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MAdventQuestTsubasaPointRewardUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MAdventQuestTsubasaPointRewardService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MAdventQuestTsubasaPointReward(123);
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
        const entity = new MAdventQuestTsubasaPointReward();
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
