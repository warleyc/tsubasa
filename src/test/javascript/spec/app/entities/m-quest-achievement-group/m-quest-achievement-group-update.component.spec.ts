/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MQuestAchievementGroupUpdateComponent } from 'app/entities/m-quest-achievement-group/m-quest-achievement-group-update.component';
import { MQuestAchievementGroupService } from 'app/entities/m-quest-achievement-group/m-quest-achievement-group.service';
import { MQuestAchievementGroup } from 'app/shared/model/m-quest-achievement-group.model';

describe('Component Tests', () => {
  describe('MQuestAchievementGroup Management Update Component', () => {
    let comp: MQuestAchievementGroupUpdateComponent;
    let fixture: ComponentFixture<MQuestAchievementGroupUpdateComponent>;
    let service: MQuestAchievementGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MQuestAchievementGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MQuestAchievementGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MQuestAchievementGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MQuestAchievementGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MQuestAchievementGroup(123);
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
        const entity = new MQuestAchievementGroup();
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
