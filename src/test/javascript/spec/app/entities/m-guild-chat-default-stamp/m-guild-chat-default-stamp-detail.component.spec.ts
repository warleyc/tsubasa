/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildChatDefaultStampDetailComponent } from 'app/entities/m-guild-chat-default-stamp/m-guild-chat-default-stamp-detail.component';
import { MGuildChatDefaultStamp } from 'app/shared/model/m-guild-chat-default-stamp.model';

describe('Component Tests', () => {
  describe('MGuildChatDefaultStamp Management Detail Component', () => {
    let comp: MGuildChatDefaultStampDetailComponent;
    let fixture: ComponentFixture<MGuildChatDefaultStampDetailComponent>;
    const route = ({ data: of({ mGuildChatDefaultStamp: new MGuildChatDefaultStamp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildChatDefaultStampDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGuildChatDefaultStampDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuildChatDefaultStampDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGuildChatDefaultStamp).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
