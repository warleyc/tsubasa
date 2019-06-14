/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuerillaQuestWorldUpdateComponent } from 'app/entities/m-guerilla-quest-world/m-guerilla-quest-world-update.component';
import { MGuerillaQuestWorldService } from 'app/entities/m-guerilla-quest-world/m-guerilla-quest-world.service';
import { MGuerillaQuestWorld } from 'app/shared/model/m-guerilla-quest-world.model';

describe('Component Tests', () => {
  describe('MGuerillaQuestWorld Management Update Component', () => {
    let comp: MGuerillaQuestWorldUpdateComponent;
    let fixture: ComponentFixture<MGuerillaQuestWorldUpdateComponent>;
    let service: MGuerillaQuestWorldService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuerillaQuestWorldUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGuerillaQuestWorldUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGuerillaQuestWorldUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuerillaQuestWorldService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGuerillaQuestWorld(123);
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
        const entity = new MGuerillaQuestWorld();
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
