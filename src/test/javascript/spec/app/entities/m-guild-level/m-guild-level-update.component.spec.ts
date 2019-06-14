/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildLevelUpdateComponent } from 'app/entities/m-guild-level/m-guild-level-update.component';
import { MGuildLevelService } from 'app/entities/m-guild-level/m-guild-level.service';
import { MGuildLevel } from 'app/shared/model/m-guild-level.model';

describe('Component Tests', () => {
  describe('MGuildLevel Management Update Component', () => {
    let comp: MGuildLevelUpdateComponent;
    let fixture: ComponentFixture<MGuildLevelUpdateComponent>;
    let service: MGuildLevelService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildLevelUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MGuildLevelUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MGuildLevelUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MGuildLevelService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MGuildLevel(123);
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
        const entity = new MGuildLevel();
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
