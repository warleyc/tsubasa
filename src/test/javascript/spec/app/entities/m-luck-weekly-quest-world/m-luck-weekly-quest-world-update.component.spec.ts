/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLuckWeeklyQuestWorldUpdateComponent } from 'app/entities/m-luck-weekly-quest-world/m-luck-weekly-quest-world-update.component';
import { MLuckWeeklyQuestWorldService } from 'app/entities/m-luck-weekly-quest-world/m-luck-weekly-quest-world.service';
import { MLuckWeeklyQuestWorld } from 'app/shared/model/m-luck-weekly-quest-world.model';

describe('Component Tests', () => {
  describe('MLuckWeeklyQuestWorld Management Update Component', () => {
    let comp: MLuckWeeklyQuestWorldUpdateComponent;
    let fixture: ComponentFixture<MLuckWeeklyQuestWorldUpdateComponent>;
    let service: MLuckWeeklyQuestWorldService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLuckWeeklyQuestWorldUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MLuckWeeklyQuestWorldUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MLuckWeeklyQuestWorldUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLuckWeeklyQuestWorldService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MLuckWeeklyQuestWorld(123);
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
        const entity = new MLuckWeeklyQuestWorld();
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
