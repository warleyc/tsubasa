/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MActionLevelUpdateComponent } from 'app/entities/m-action-level/m-action-level-update.component';
import { MActionLevelService } from 'app/entities/m-action-level/m-action-level.service';
import { MActionLevel } from 'app/shared/model/m-action-level.model';

describe('Component Tests', () => {
  describe('MActionLevel Management Update Component', () => {
    let comp: MActionLevelUpdateComponent;
    let fixture: ComponentFixture<MActionLevelUpdateComponent>;
    let service: MActionLevelService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionLevelUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MActionLevelUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MActionLevelUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MActionLevelService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MActionLevel(123);
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
        const entity = new MActionLevel();
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
