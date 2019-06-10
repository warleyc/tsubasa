/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCardLevelUpdateComponent } from 'app/entities/m-card-level/m-card-level-update.component';
import { MCardLevelService } from 'app/entities/m-card-level/m-card-level.service';
import { MCardLevel } from 'app/shared/model/m-card-level.model';

describe('Component Tests', () => {
  describe('MCardLevel Management Update Component', () => {
    let comp: MCardLevelUpdateComponent;
    let fixture: ComponentFixture<MCardLevelUpdateComponent>;
    let service: MCardLevelService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardLevelUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCardLevelUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCardLevelUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCardLevelService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCardLevel(123);
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
        const entity = new MCardLevel();
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
