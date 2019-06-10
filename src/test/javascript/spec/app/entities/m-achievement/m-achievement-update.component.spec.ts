/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAchievementUpdateComponent } from 'app/entities/m-achievement/m-achievement-update.component';
import { MAchievementService } from 'app/entities/m-achievement/m-achievement.service';
import { MAchievement } from 'app/shared/model/m-achievement.model';

describe('Component Tests', () => {
  describe('MAchievement Management Update Component', () => {
    let comp: MAchievementUpdateComponent;
    let fixture: ComponentFixture<MAchievementUpdateComponent>;
    let service: MAchievementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAchievementUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MAchievementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MAchievementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MAchievementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MAchievement(123);
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
        const entity = new MAchievement();
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
